package com.ceng316.internshipmanagementsystemapi.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceng316.internshipmanagementsystemapi.entities.Student;
import com.ceng316.internshipmanagementsystemapi.services.SecretaryService;

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

    @GetMapping("/studentList/download")
    public ResponseEntity<byte[]> downloadEligibleStudents() throws IOException {
        System.out.println("Start of the method");
        List<Student> students = secretaryService.getEligibleStudentsList();
        System.out.println("Students are fetched");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(outputStream);
        System.out.println("Writer and outputstream is created");
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Email", "Name", "Grade"))) {
            System.out.println("Inside try");
            for (Student student : students) {
                csvPrinter.printRecord(student.getStudentID(), student.getEmail(), student.getName(), student.getGrade());
            }
            System.out.println("After for loop");
        } catch (IOException e) {
            System.out.println("Error while writing to csv");
            System.out.println(e.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        System.out.println("Headers are created");
        headers.setContentDispositionFormData("attachment", "students.csv");
        System.out.println("Content disposition is set");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        System.out.println("Content type is set");

        return ResponseEntity.ok().headers(headers).body(outputStream.toByteArray());
    }

}
