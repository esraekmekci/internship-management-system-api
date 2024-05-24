package com.ceng316.internshipmanagementsystemapi.controllers;

import java.util.List;

import com.ceng316.internshipmanagementsystemapi.entities.Coordinator;
import com.ceng316.internshipmanagementsystemapi.entities.Secretary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ceng316.internshipmanagementsystemapi.entities.Student;
import com.ceng316.internshipmanagementsystemapi.responses.StudentSGKResponse;
import com.ceng316.internshipmanagementsystemapi.services.SecretaryService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/secretary")
public class SecretaryController {
    SecretaryService secretaryService;

    public SecretaryController(SecretaryService secretaryService) {
        this.secretaryService = secretaryService;
    }

    @GetMapping("/studentList")
    public List<Student> getEligibleStudents() {
        return secretaryService.getEligibleStudentsList();
    }

    @GetMapping("/studentListWithStatus")
    public List<StudentSGKResponse> getEligibleStudentsWithStatus() {
        return secretaryService.getEligibleStudentsWithStatus();
    }

    @GetMapping("/studentList/download")
    public ResponseEntity<byte[]> downloadEligibleStudents() {
        return secretaryService.downloadEligibleStudents();
    }

    @PostMapping("/{studentId}/uploadSGK")
    public String uploadSGK(@RequestParam MultipartFile file, @PathVariable Long studentId) throws Exception {
        try {
            secretaryService.uploadSGK(file, studentId);
            return "File uploaded";
        }
        catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{studentId}/deleteSGK")
    public String deleteSGK(@PathVariable Long studentId) {
        try {
            secretaryService.deleteSGK(studentId);
            return "File deleted";
        }
        catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/token/{token}")
    public Secretary getSecretaryByToken(@PathVariable String token){
        return secretaryService.getSecretaryByToken(token);
    }
}
