package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companyreps")
public class CompanyRep extends User {

    @Builder(builderMethodName = "companyRepBuilder")
    public CompanyRep(Long id, String name, String email, String password, String role, String companyName, String companyAddress, String foundationYear, String employeeSize, String internshipType) {
        super(name, email, password, role);
        this.id = id;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.foundationYear = foundationYear;
        this.employeeSize = employeeSize;
        this.internshipType = internshipType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String companyName;
    String companyAddress;
    String foundationYear;
    String employeeSize;
    String internshipType;


//    @OneToMany
//    List<Student> studentList;

    @Override
    public Long getSubclassId() {
        return id;
    }
}
