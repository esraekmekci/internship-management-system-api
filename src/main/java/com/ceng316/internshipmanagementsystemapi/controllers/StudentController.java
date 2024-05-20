package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.*;
import com.ceng316.internshipmanagementsystemapi.responses.ApplicationForStudentResponse;
import com.ceng316.internshipmanagementsystemapi.services.StudentService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable Long studentId) {
        return studentService.getStudent(studentId);
    }

    @GetMapping("/token/{token}")
    public User getStudentByToken(@PathVariable String token){
        return studentService.getStudentByToken(token);
    }

    @GetMapping("/{studentId}/appliedcompanies")
    public List<ApplicationForStudentResponse> getAppliedCompanies(@PathVariable Long studentId) {
        return studentService.getAppliedCompanies(studentId);
    }

    @PostMapping("/{studentId}/uploadApplicationLetter")
    public String uploadApplicationLetter(@RequestParam MultipartFile file,
                                        @RequestParam String companyName,
                                        @PathVariable Long studentId) throws Exception {
        try {
            studentService.uploadApplicationLetter(file, companyName, studentId);
            return "File uploaded";
        }
        catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{studentId}/downloadApplicationLetter")
    public ResponseEntity<Resource> downloadApplicationLetter(@PathVariable Long studentId,
                                                              @RequestParam String companyName) {
        return studentService.downloadApplicationLetter(studentId, companyName);
    }

    @PostMapping("/{studentId}/uploadApplicationForm")
    public String uploadApplicationForm(@RequestParam MultipartFile file,
                                        @RequestParam String companyName,
                                        @PathVariable Long studentId) throws Exception {
        try {
            studentService.uploadApplicationForm(file, companyName, studentId);
            return "File uploaded";
        }
        catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{studentId}/downloadApplicationForm")
    public ResponseEntity<Resource> downloadApplicationForm(@PathVariable Long studentId,
                                                              @RequestParam String companyName) {
        return studentService.downloadApplicationForm(studentId, companyName);
    }

    @GetMapping("/{studentId}/downloadSGKDocument")
    public ResponseEntity<Resource> downloadSGKDocument(@PathVariable Long studentId) {
        return studentService.downloadSGKDocument(studentId);
    }
}
