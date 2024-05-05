package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.User;
import com.ceng316.internshipmanagementsystemapi.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping("/token/{token}")
    public User getUserByToken(@PathVariable String token){
        return userService.getUserByToken(token);
    }
}
