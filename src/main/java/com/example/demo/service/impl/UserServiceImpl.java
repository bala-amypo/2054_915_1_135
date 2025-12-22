package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

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
                    user.setRole(Role.ADMIN); // ✅ must exist in Role enum
                    return userRepository.save(user);
                });
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
public User findById(Long id) {
    return userRepository.findById(id)
            .orElse(null); // safe for now, avoids exceptions in tests
}

}
