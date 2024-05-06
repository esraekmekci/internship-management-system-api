package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.*;
import com.ceng316.internshipmanagementsystemapi.services.StudentService;
import com.ceng316.internshipmanagementsystemapi.services.UserService;
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

    @PostMapping("/{studentId}/uploadApplicationLetter")
    public void uploadApplicationLetter(@RequestParam MultipartFile file,
                                        @RequestParam String companyName,
                                        @PathVariable Long studentId) {
        studentService.uploadApplicationLetter(file, companyName, studentId);
    }

    @GetMapping("/{studentId}/downloadApplicationLetter")
    public ResponseEntity<Resource> downloadApplicationLetter(@PathVariable Long studentId,
                                                              @RequestParam String companyName) {
        return studentService.downloadApplicationLetter(studentId, companyName);
    }
}
