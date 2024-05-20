package com.ceng316.internshipmanagementsystemapi.services;

import java.util.List;

import com.ceng316.internshipmanagementsystemapi.controllers.S3Controller;
import com.ceng316.internshipmanagementsystemapi.repos.SGKRepository;
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
        return studentRepo.findByNationalityAndInternshipStatusAndCompanyAddress("Turkish", "Approved", "TR");
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
