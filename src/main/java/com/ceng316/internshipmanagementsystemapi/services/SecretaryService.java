package com.ceng316.internshipmanagementsystemapi.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.ceng316.internshipmanagementsystemapi.controllers.S3Controller;
import com.ceng316.internshipmanagementsystemapi.controllers.SecretaryController;
import com.ceng316.internshipmanagementsystemapi.entities.SGKFile;
import com.ceng316.internshipmanagementsystemapi.repos.SGKRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ceng316.internshipmanagementsystemapi.entities.Student;
import com.ceng316.internshipmanagementsystemapi.repos.SecretaryRepository;
import com.ceng316.internshipmanagementsystemapi.repos.StudentRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SecretaryService {
    SecretaryRepository secretaryRepo;
    StudentRepository studentRepo;
    SGKRepository sgkRepo;
    S3Controller s3Controller;

    public SecretaryService(SecretaryRepository secretaryRepo, StudentRepository studentRepo, SGKRepository sgkRepo, S3Controller s3Controller) {
        this.secretaryRepo = secretaryRepo;
        this.studentRepo = studentRepo;
        this.sgkRepo = sgkRepo;
        this.s3Controller = s3Controller;
    }
    public List<Student> getEligibleStudentsList() {
        List<Student> studentList = new ArrayList<>();
        List<SGKFile> sgkFileList = sgkRepo.findAll();
        for (SGKFile sgk:sgkFileList) {
            studentList.add(studentRepo.findById(sgk.getStudentId()).orElse(null));
        }
        return studentList;
        // return studentRepo.findByNationalityAndInternshipStatusAndCompanyAddress("Turkish", "Approved", "TR");
    }

    public ResponseEntity<byte[]> downloadEligibleStudents() {
        List<Student> students = getEligibleStudentsList();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(outputStream);
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Email", "Name", "Grade"))) {
            for (Student student : students) {
                csvPrinter.printRecord(student.getStudentID(), student.getEmail(), student.getName(), student.getGrade());
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "students.csv");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok().headers(headers).body(outputStream.toByteArray());
    }

    public void uploadSGK(MultipartFile file, Long studentId) throws Exception{
        try {
            String fileName = "SGK_Report_" + studentId;
            s3Controller.uploadFile(file, fileName);
            sgkRepo.updateSGKDocumentStatus(studentId, "Available");
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    public void deleteSGK(Long studentId) throws Exception {
        try {
            String fileName = "SGK_Report_" + studentId;
            s3Controller.deleteFile(fileName);
            sgkRepo.updateSGKDocumentStatus(studentId, "Unavailable");
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}
