package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.repos.CompanyRepRepository;
import com.ceng316.internshipmanagementsystemapi.repos.CoordinatorRepository;
import com.ceng316.internshipmanagementsystemapi.repos.SecretaryRepository;
import com.ceng316.internshipmanagementsystemapi.repos.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    CoordinatorRepository coordinatorRepo;
    CompanyRepRepository companyRepRepo;
    StudentRepository studentRepo;
    SecretaryRepository secretaryRepo;

    void authUser() {}
    void loginAsStudent(String email, String password) {}
    void loginAsCoordinator(String email, String password) {}
    void loginAsSecretary(String email, String password) {}
    void loginAsCompanyRep(String email, String password) {}

    void createUser(){}

}
