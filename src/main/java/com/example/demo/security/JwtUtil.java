package com.example.demo.security;

public class JwtUtil {

    public JwtUtil(String secret, long validityInMs) {
    }

    public String generateToken(Long userId, String email, String role) {
        return "DUMMY_TOKEN";
    }
}
