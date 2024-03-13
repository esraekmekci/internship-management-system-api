package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.entities.Student;
import com.ceng316.internshipmanagementsystemapi.repos.CoordinatorRepository;

public class CoordinatorService {
    CoordinatorRepository coordinatorRepo;
    void approveDocument(Document doc) {}
    void announceGrade() {}
    void enterGrade(Student student, String grade) {}
    void getPendingDocuments() {}
    void announceGuidelines(Document doc) {}
    void rejectDocument(Document doc) {}
}
