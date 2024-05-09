package com.ceng316.internshipmanagementsystemapi.requests;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;
    String role;
}
