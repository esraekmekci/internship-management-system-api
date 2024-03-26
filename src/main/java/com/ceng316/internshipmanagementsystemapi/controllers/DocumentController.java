package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.services.DocumentService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;


public class DocumentController {
    DocumentService documentService;

    void uploadDocument(Document document) {
    }

    ResponseEntity<Resource> downloadDocument(String documentName) {
        return null;
    }

    void updateDocument(Document document) {
    }

    void changeStatus(Document document, String newStatus) {
    }
}
