package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "secretaries")
public class Secretary extends  User {

    @Builder(builderMethodName = "secretaryBuilder")
    public Secretary(String name, String email, String password, String role) {
        super(name, email, password, role);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getSubclassId() {
        return id;
    }
}
