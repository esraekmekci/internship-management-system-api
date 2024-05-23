package com.ceng316.internshipmanagementsystemapi.services;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.ceng316.internshipmanagementsystemapi.controllers.S3Controller;
import com.ceng316.internshipmanagementsystemapi.entities.Application;
import com.ceng316.internshipmanagementsystemapi.entities.SGKFile;
import com.ceng316.internshipmanagementsystemapi.repos.ApplicationRepository;
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
import com.ceng316.internshipmanagementsystemapi.responses.StudentSGKResponse;

import org.springframework.web.multipart.MultipartFile;

@Service
public class SecretaryService {
    SecretaryRepository secretaryRepo;
    StudentRepository studentRepo;
    SGKRepository sgkRepo;
    S3Controller s3Controller;
    ApplicationRepository applicationRepo;

    public SecretaryService(SecretaryRepository secretaryRepo, StudentRepository studentRepo, SGKRepository sgkRepo, S3Controller s3Controller, ApplicationRepository applicationRepo) {
        this.secretaryRepo = secretaryRepo;
        this.studentRepo = studentRepo;
        this.sgkRepo = sgkRepo;
        this.s3Controller = s3Controller;
        this.applicationRepo = applicationRepo;
    }
    public List<Student> getEligibleStudentsList() {
        List<Student> sL1 = studentRepo.findByNationalityAndInternshipStatusAndCompanyAddress("Turkish", "Application Form Approved", "TR");
        List<Student> sL2 = studentRepo.findByNationalityAndInternshipStatusAndCompanyAddress("Turkish", "SGK Document Pending", "TR");
        List<Student> sL3 = studentRepo.findByNationalityAndInternshipStatusAndCompanyAddress("Turkish", "SGK Document Uploaded", "TR");
        List<Student> students = new ArrayList<>();
        students.addAll(sL1);
        students.addAll(sL2);
        students.addAll(sL3);
        return students;
    }

    public List<StudentSGKResponse> getEligibleStudentsWithStatus() {
        List<Student> studentList = getEligibleStudentsList();
        List<StudentSGKResponse> studentSGKResponseList = new ArrayList<>();
        for (Student s : studentList) {
            SGKFile file = sgkRepo.findByStudentId(s.getStudentID());
            if (file != null) {
                String status = file.getSgkDocumentStatus();
                studentSGKResponseList.add(new StudentSGKResponse(s.getStudentID(), s.getEmail(), s.getName(), status));
            }
        }
        return studentSGKResponseList;
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
            sgkRepo.updateSGKDocumentStatus(studentId, "SGK Document Uploaded");
            Application application = applicationRepo.findByStudentIdAndApplicationStatus(studentId, "Application Form Approved");
            if (application == null) {
                application = applicationRepo.findByStudentIdAndApplicationStatus(studentId, "SGK Document Pending");
            }
            if (application != null) {
                application.setApplicationStatus("SGK Document Uploaded");
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    public void deleteSGK(Long studentId) throws Exception {
        try {
            String fileName = "SGK_Report_" + studentId;
            s3Controller.deleteFile(fileName);
            sgkRepo.updateSGKDocumentStatus(studentId, "SGK Document Pending");
            Application application = applicationRepo.findByStudentIdAndApplicationStatus(studentId, "SGK Document Uploaded");
            if (application != null) {
                application.setApplicationStatus("SGK Document Pending");
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}
