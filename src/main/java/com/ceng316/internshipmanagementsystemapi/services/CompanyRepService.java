package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.repos.CompanyRepRepository;

import java.util.List;

public class CompanyRepService {
    CompanyRepRepository representativeRepo;
    DocumentService documentService;

    List<CompanyRep> getAllCompanyReps() {
        return null;
    }

    CompanyRep getCompanyRep(Long id) {
        return null;
    }
    void getPendingDocuments() {}
    void uploadDocument(Document doc) {}
    void rejectDocument(Document doc) {}
    void approveDocument(Document doc) {}
    void deleteAnnouncement(Document doc) {}
    void enterAnnouncement(Document doc) {}
}
