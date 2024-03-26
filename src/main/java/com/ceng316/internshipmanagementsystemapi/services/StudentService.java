package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.entities.SGKCertificate;
import com.ceng316.internshipmanagementsystemapi.entities.Student;
import com.ceng316.internshipmanagementsystemapi.repos.StudentRepository;

import java.util.List;

public class StudentService {
    StudentRepository studentRepo;
    SGKService sgkService;
    DocumentService documentService;
    List<Student> getAllStudents() {
        return null;
    }
    Student getStudent(Long id) {
        return null;
    }

    void createStudent(Student student) {}
    void uploadDocument(Document doc) {}
    void downloadDocument(Document doc) {}
    void addApplicationLetter(Document doc) {
        //change internshipStatus here
    }
    void addApplicationForm(Document doc) {
        //change internshipStatus here
    }
    void addInternshipReport(Document doc) {
        //change internshipStatus here
    }
    boolean verifyCredentials() {
        return false;
    }
    List<CompanyRep> getAppliedCompanies() {
        return null;
    }

    CompanyRep getCompanyRep(Long id) {
        return null;
    }

    SGKCertificate getSGKCertificate() {
        return null;
    }
    Document getInternshipReport() {return null};
}
