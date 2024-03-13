package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "companyreps")
public class CompanyRep extends User{
    String companyName;
    List<Student> studentList;
}
