package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.services.CompanyRepService;

import java.util.List;

public class CompanyRepController {
    CompanyRepService representativeService;
    List<CompanyRep> getAllCompanyReps() {
        return null;
    }

    CompanyRep getCompanyRep(Long id) {
        return null;
    }
    void enterOpportunity(Document doc) {}
    void deleteOpportunity(Document doc) {}
    void approveDocument(Document doc) {}
    void rejectDocument(Document doc) {}
    void uploadDocument(Document doc) {}
    void downloadDocument(Document doc) {}
    void getPendingDocuments() {}
}
