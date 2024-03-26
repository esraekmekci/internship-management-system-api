package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.repos.DocumentRepository;

public class DocumentService {
    DocumentRepository documentRepo;
    AmazonS3 s3Client;

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
