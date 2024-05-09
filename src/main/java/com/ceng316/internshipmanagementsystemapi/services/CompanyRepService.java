package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.repos.CompanyRepRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyRepService {
    CompanyRepRepository companyRepRepo;

    public CompanyRepService(CompanyRepRepository companyRepRepo) {
        this.companyRepRepo = companyRepRepo;
    }

    public List<CompanyRep> getAllCompanyReps() {
        return companyRepRepo.findAll();
    }

    public CompanyRep getCompanyRep(Long id) {
        return companyRepRepo.findById(id).orElse(null);
    }

}
