package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.entities.Student;
import com.ceng316.internshipmanagementsystemapi.services.CoordinatorService;

public class CoordinatorController {
    CoordinatorService coordinatorService;
    void approveDocument(Document doc) {}
    void rejectDocument(Document doc) {}
    void announceGuidelines(Document doc) {}
    void getPendingDocuments() {}
    void enterGrade(Student student, String grade) {}
    void announceGrade() {}
}
