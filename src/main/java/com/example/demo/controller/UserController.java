package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE USER (POST)
    @PostMapping
    public ApiResponse createUser(@RequestBody User user) {
        User saved = userService.register(user);
        return new ApiResponse(true, "User created successfully", saved);
    }

    // GET SINGLE USER
    @GetMapping("/{id}")
    public ApiResponse getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return new ApiResponse(true, "User fetched", user);
    }

    // GET ALL USERS
    @GetMapping
    public ApiResponse getAll() {
        List<User> users = userService.getAllUsers();
        return new ApiResponse(true, "Users fetched", users);
    }
}
