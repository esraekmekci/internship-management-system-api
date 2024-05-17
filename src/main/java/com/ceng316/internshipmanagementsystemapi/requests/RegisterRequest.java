package com.ceng316.internshipmanagementsystemapi.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    // this is only for companies
    private String compName;
    private String compAddress;
    private String foundationYear;
    private String empSize;
    private String email;
    private String name;
    private String password;
    private String role;
}
