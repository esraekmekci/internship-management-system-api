package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    int studentID;
    String grade;
    String internshipStatus; // can this be boolean or enum?

    @OneToMany
    List<Document> applicationLetters;

    @OneToMany
    List<Document> applicationForms;
    @OneToOne
    Document companyForm;
    @OneToOne
    Document internshipReport;

    @OneToOne
    CompanyRep companyRep;
}
