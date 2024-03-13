package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student extends User {
    @Id
    int studentID;
    String grade;
    String internshipStatus; // can this be boolean or enum?
    Document applicationLetter;
    Document applicationForm;
    Document companyForm;
    Document internshipReport;
    Document sgkDocument;
}
