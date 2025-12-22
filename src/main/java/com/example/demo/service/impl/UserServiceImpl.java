package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {

        return userRepository.findByEmail(user.getEmail())
                .orElseGet(() -> {
                    user.setRole(Role.USER);   // ✅ enum, not string
                    return userRepository.save(user);
                });
    }
}
