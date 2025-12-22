package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;

public interface UserService {

    User register(RegisterRequest request);

    User login(LoginRequest request);

    List<User> getAllUsers();

    User findById(Long id);

    User findByEmail(String email);
}
