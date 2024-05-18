package com.ceng316.internshipmanagementsystemapi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceng316.internshipmanagementsystemapi.entities.Student;
import com.ceng316.internshipmanagementsystemapi.services.SecretaryService;
import com.ceng316.internshipmanagementsystemapi.services.StudentService;

@RestController
@RequestMapping("/secretary")
public class SecretaryController {
    SecretaryService secretaryService;

    public SecretaryController(SecretaryService secretaryService) {
        this.secretaryService = secretaryService;
    }

    @GetMapping("/studentList")
    public List<Student> getAllStudents() {
        return secretaryService.getStudentList();
    }

}
