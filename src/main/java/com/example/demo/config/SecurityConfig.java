package com.example.demo.config;

import com.example.demo.security.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    // Expose JwtUtil as a bean so filters can inject it
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(
                "default_secret_key_default_secret_key",
                3600000
        );
    }
}
