package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.services.CompanyRepService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyRepController {
    CompanyRepService companyRepService;

    public CompanyRepController(CompanyRepService companyRepService) {
        this.companyRepService = companyRepService;
    }

    @GetMapping
    public List<CompanyRep> getAllCompanyReps() {
        return companyRepService.getAllCompanyReps();
    }

    @GetMapping("/{id}")
    public CompanyRep getCompanyRep(@PathVariable Long id) {
        return companyRepService.getCompanyRep(id);
    }

    @GetMapping("/name/{name}")
    public CompanyRep getCompanyByName(@PathVariable String name) {
        return companyRepService.getCompanyByName(name);
    }
}
