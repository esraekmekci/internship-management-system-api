package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String name;
    @Column(unique = true)
    String email;
    String password;
    String role;

    public Long getSubclassId() {
        return null;
    }
}
