package com.example.demo.controller;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
public User register(@RequestBody RegisterRequest request) {

    User user = new User();

    user.setFullName(request.fullName);
    user.setEmail(request.email);
    user.setPassword(request.password);
    user.setRole(request.role);

    return userService.registerUser(user);
}


    // 🔹 GET /api/users/{id}
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 🔹 GET /api/users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
