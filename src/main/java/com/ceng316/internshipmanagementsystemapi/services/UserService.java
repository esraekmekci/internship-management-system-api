package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.entities.Secretary;
import com.ceng316.internshipmanagementsystemapi.entities.User;
import com.ceng316.internshipmanagementsystemapi.repos.*;
import com.ceng316.internshipmanagementsystemapi.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    StudentRepository studentRepository;
    CoordinatorRepository coordinatorRepository;
    SecretaryRepository secretaryRepository;
    CompanyRepRepository companyRepRepository;
    JwtTokenProvider jwtTokenProvider;

    public UserService(StudentRepository studentRepository, CoordinatorRepository coordinatorRepository, SecretaryRepository secretaryRepository, CompanyRepRepository companyRepRepository, JwtTokenProvider jwtTokenProvider) {
        this.studentRepository = studentRepository;
        this.coordinatorRepository = coordinatorRepository;
        this.secretaryRepository = secretaryRepository;
        this.companyRepRepository = companyRepRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public List<User> getAllUsers(String role) {
        return switch (role) {
            case "STUDENT" -> studentRepository.findAll().stream().map(student -> (User) student).toList();
            case "COORDINATOR" -> coordinatorRepository.findAll().stream().map(coordinator -> (User) coordinator).toList();
            case "SECRETARY" -> secretaryRepository.findAll().stream().map(secretary -> (User) secretary).toList();
            case "COMPANYREP" -> companyRepRepository.findAll().stream().map(companyRep -> (User) companyRep).toList();
            default -> null;
        };
    }

    public User getUserById(Long id, String role) {
        return switch (role) {
            case "STUDENT" -> studentRepository.findById(id).orElse(null);
            case "COORDINATOR" -> coordinatorRepository.findById(id).orElse(null);
            case "SECRETARY" -> secretaryRepository.findById(id).orElse(null);
            case "COMPANYREP" -> companyRepRepository.findById(id).orElse(null);
            default -> null;
        };
    }

    public User getUserByEmail(String email, String role) {
        return switch (role) {
            case "STUDENT" -> studentRepository.findByEmail(email);
            case "COORDINATOR" -> coordinatorRepository.findByEmail(email);
            case "SECRETARY" -> secretaryRepository.findByEmail(email);
            case "COMPANYREP" -> companyRepRepository.findByEmail(email);
            default -> null;
        };
    }

    public User getUserByToken(String token, String role) {
        Long userId = jwtTokenProvider.getUserIdFromJwt(token);
        return switch (role) {
            case "STUDENT" -> studentRepository.findById(userId).orElse(null);
            case "COORDINATOR" -> coordinatorRepository.findById(userId).orElse(null);
            case "SECRETARY" -> secretaryRepository.findById(userId).orElse(null);
            case "COMPANYREP" -> companyRepRepository.findById(userId).orElse(null);
            default -> null;
        };
    }

}
