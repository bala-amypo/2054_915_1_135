package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setUsername(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));

        userService.register(user);

        return new ApiResponse(true, "User registered successfully");
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest request) {

        User user = userService.findByEmail(request.getEmail());

        String token = jwtUtil.generateToken(
                user.getId(),
                user.getUsername(),
                user.getRole().name()
        );

        return new ApiResponse(true, token);
    }
}
