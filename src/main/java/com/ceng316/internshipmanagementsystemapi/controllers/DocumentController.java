package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.entities.User;
import com.ceng316.internshipmanagementsystemapi.services.DocumentService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

//    @GetMapping("/templates")
//    public List<User> getAllDocumentTemplates(){
//        return documentService.getAllDocumentTemplates();
//    }

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
