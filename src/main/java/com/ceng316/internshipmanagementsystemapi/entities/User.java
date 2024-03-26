package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public abstract class User {
    @Id
    Long id;
    String name;
    String email;
    String password;
}
