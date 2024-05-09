package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
@Table(name = "documents")
public class Document {
    @Id
    int documentID;
    String status; // enum olabilir mi
    String type; // enum olabilir mi
    Date uploadDate;

    @OneToOne
    Student student;

    @OneToOne
    CompanyRep company;

    @OneToOne
    Coordinator coordinator;
}
