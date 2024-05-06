package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.User;
import com.ceng316.internshipmanagementsystemapi.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(@RequestParam String role){
        return userService.getAllUsers(role);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id, @RequestParam String role){
        return userService.getUserById(id,role);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email, @RequestParam String role){
        return userService.getUserByEmail(email,role);
    }

    @GetMapping("/token/{token}")
    public User getUserByToken(@PathVariable String token, @RequestParam String role){
        return userService.getUserByToken(token, role);
    }
}
