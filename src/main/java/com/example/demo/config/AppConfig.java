package com.example.demo.config;

import com.example.demo.repository.*;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.*;
import com.example.demo.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔹 ADD THIS BEAN (THIS FIXES YOUR ERROR)
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil("secret-key-123", 24 * 60 * 60 * 1000);
    }

    @Bean
    public UserService userService(UserRepository userRepository,
                                   PasswordEncoder passwordEncoder) {
        return new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Bean
    public EventService eventService(EventRepository eventRepository,
                                     UserRepository userRepository) {
        return new EventServiceImpl(eventRepository, userRepository);
    }

    @Bean
    public SubscriptionService subscriptionService(SubscriptionRepository subRepo,
                                                    UserRepository userRepo,
                                                    EventRepository eventRepo) {
        return new SubscriptionServiceImpl(subRepo, userRepo, eventRepo);
    }

    @Bean
    public BroadcastService broadcastService(EventUpdateRepository updateRepo,
                                              SubscriptionRepository subRepo,
                                              BroadcastLogRepository logRepo) {
        return new BroadcastServiceImpl(updateRepo, subRepo, logRepo);
    }
@Bean
   public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository,
                              EventRepository eventRepository,
                              BroadcastService broadcastService) {
    this.eventUpdateRepository = eventUpdateRepository;
    this.eventRepository = eventRepository;
    this.broadcastService = broadcastService;
}

}
