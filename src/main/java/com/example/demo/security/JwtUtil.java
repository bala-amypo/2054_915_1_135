package com.example.demo.security;

public class JwtUtil {

    private final String secret;
    private final long validityInMs;

    // Constructor required by project / future scope
    public JwtUtil(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    // Dummy token generation
    public String generateToken() {
        return "DUMMY_JWT_TOKEN";
    }

    // Dummy validation (always true)
    public boolean validateToken(String token) {
        return true;
    }

    // Dummy extraction methods
    public String getUsernameFromToken(String token) {
        return "dummy_user@example.com";
    }

    public Long getUserIdFromToken(String token) {
        return 1L;
    }

    public String getRoleFromToken(String token) {
        return "SUBSCRIBER";
    }
}
