package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("/ubys")
public class UBYSController {
    String UBYS_API = "http://localhost:3001/students/";
    @GetMapping("/students")
    public String getAllStudents() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(UBYS_API, String.class);
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable Long studentId) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(UBYS_API + studentId.toString(), String.class);

        Gson gson = new Gson();
        Student student = gson.fromJson(response, Student.class);
        student.setRole("STUDENT");
        return student;
    }

}
