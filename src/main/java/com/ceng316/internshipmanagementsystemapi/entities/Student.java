package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import com.ceng316.internshipmanagementsystemapi.entities.Document;

import java.util.List;

@Data
@Entity
@Table(name = "students")
public class Student extends User {
    @Id
    int studentID;
    String grade;
    String internshipStatus; // can this be boolean or enum?

    @OneToMany(mappedBy = "student")
    List<Document> applicationLetter;

    @OneToOne(mappedBy = "student")
    Document applicationForm;
    @OneToOne(mappedBy = "student")
    Document companyForm;
    @OneToOne(mappedBy = "student")
    Document internshipReport;
    @OneToOne(mappedBy = "student")
    Document sgkDocument;
}
