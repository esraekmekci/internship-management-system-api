package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "companyreps")
public class CompanyRep extends User{
    @Id
    private Long id;

    String companyName;

    @OneToMany(mappedBy = "companyreps")
    List<Student> studentList;
}
