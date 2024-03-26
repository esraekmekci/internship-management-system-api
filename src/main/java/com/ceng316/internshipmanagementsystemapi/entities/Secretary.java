package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "secretaries")
public class Secretary extends User {
    @Id
    private Long id;
}
