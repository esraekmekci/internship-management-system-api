package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.repos.DocumentRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    DocumentRepository documentRepo;

    public DocumentService(DocumentRepository documentRepo) {
        this.documentRepo = documentRepo;
    }


    void changeStatus(Document document, String newStatus) {
    }

    void updateDocument(Document document) {
    }

    void downloadDocument() {
    }

    void uploadDocument(Document document) {
    }

    void notifyCompanyRep() {
    }

    void notifyStudent() {
    }

    void notifyCoordinator() {
    }
}
