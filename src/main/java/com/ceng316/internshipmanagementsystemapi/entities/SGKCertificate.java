package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "sgk")
public class SGKCertificate {
    @Id
    int id;
    String name;
    String status;

    @OneToOne
    Student student;

    @OneToOne
    Secretary secretary;
}
