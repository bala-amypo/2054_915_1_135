package com.example.demo.service;
import org.springframework.stereotype.Service;


import com.example.demo.entity.User;
import java.util.List;
@Service
public interface UserService {

    User register(User user);

    User findByEmail(String email);

    User findById(Long id);

    List<User> getAllUsers();
}
