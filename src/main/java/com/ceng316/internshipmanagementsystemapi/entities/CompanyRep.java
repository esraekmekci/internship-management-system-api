package com.ceng316.internshipmanagementsystemapi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companyreps")
public class CompanyRep extends User {

    @Builder(builderMethodName = "companyRepBuilder")
    public CompanyRep(Long id, String name, String email, String password, String role, String companyName, String companyAddress, String foundationYear, String employeeSize, String internshipType, String accountStatus) {
        super(name, email, password, role);
        this.companyid = id;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.foundationYear = foundationYear;
        this.employeeSize = employeeSize;
        this.internshipType = internshipType;
        this.accountStatus = accountStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyid;

    String companyName;
    String companyAddress;
    String foundationYear;
    String employeeSize;
    String internshipType;
    String accountStatus;

    @Override
    public Long getSubclassId() {
        return companyid;
    }
}
