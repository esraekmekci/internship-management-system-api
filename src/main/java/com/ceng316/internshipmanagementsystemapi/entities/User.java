package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


public abstract class User {
    @Id
    int userID;
    String name;
    String email;
    String password;
}
