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
import org.springframework.web.bind.annotation.*;

import com.ceng316.internshipmanagementsystemapi.entities.Student;
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

    @GetMapping("/studentList/download")
    public ResponseEntity<byte[]> downloadEligibleStudents() throws IOException {
        List<Student> students = secretaryService.getEligibleStudentsList();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(outputStream);
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Email", "Name", "Grade"))) {
            for (Student student : students) {
                csvPrinter.printRecord(student.getStudentID(), student.getEmail(), student.getName(), student.getGrade());
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "students.csv");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok().headers(headers).body(outputStream.toByteArray());
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
}
