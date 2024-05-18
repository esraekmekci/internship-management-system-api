package com.ceng316.internshipmanagementsystemapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ceng316.internshipmanagementsystemapi.entities.Student;
import com.ceng316.internshipmanagementsystemapi.repos.SecretaryRepository;
import com.ceng316.internshipmanagementsystemapi.repos.StudentRepository;

@Service
public class SecretaryService {
    SecretaryRepository secretaryRepo;
    StudentRepository studentRepo;

    public SecretaryService(SecretaryRepository secretaryRepo, StudentRepository studentRepo) {
        this.secretaryRepo = secretaryRepo;
        this.studentRepo = studentRepo;
    }

    public List<Student> getStudentList() {
        return studentRepo.findAll();
    }

}
