package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.controllers.S3Controller;
import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.entities.SGKCertificate;
import com.ceng316.internshipmanagementsystemapi.entities.Student;
import com.ceng316.internshipmanagementsystemapi.repos.StudentRepository;
import com.ceng316.internshipmanagementsystemapi.security.JwtTokenProvider;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StudentService {
    StudentRepository studentRepo;
    S3Controller s3Controller;
    JwtTokenProvider jwtTokenProvider;

    public StudentService(StudentRepository studentRepo, S3Controller s3Controller, JwtTokenProvider jwtTokenProvider) {
        this.studentRepo = studentRepo;
        this.s3Controller = s3Controller;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudent(Long id) {
        return studentRepo.findById(id).orElse(null);
    }

    public Student getStudentByToken(String token) {
        Long userId = jwtTokenProvider.getUserIdFromJwt(token);
        return studentRepo.findById(userId).orElse(null);
    }

    public void uploadApplicationLetter(MultipartFile file, String companyName, Long studentId) {
        try {
            String fileName = "1_TR_SummerPracticeApplicationLetter2023_" + companyName + "_" + studentId + ".docx";
            s3Controller.uploadFile(file, fileName);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public ResponseEntity<Resource> downloadApplicationLetter(Long studentId, String companyName) {
        try {
            String fileName = "1_TR_SummerPracticeApplicationLetter2023_" + companyName + "_" + studentId + ".docx";
            return s3Controller.downloadFile(fileName);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

}
