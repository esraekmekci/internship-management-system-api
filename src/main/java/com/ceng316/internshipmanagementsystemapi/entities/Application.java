package com.ceng316.internshipmanagementsystemapi.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Data
@Table(name = "student_applications")
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    CompanyRep company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Student student;

    @Column(name = "application_status")
    private String applicationStatus;

    /*
    "Application Letter Pending"
    "Application Letter Approved"
    "Application Letter Rejected"
    "Application Form Sent to Company"
    "Application Form Sent to Coordinator"
    "Application Form Approved"
    "Application Form Rejected"
     */
}