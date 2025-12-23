package com.example.demo.config;

import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceConfig {

    @Bean
    public UserService userService(UserRepository userRepository,
                                   PasswordEncoder passwordEncoder) {
        return new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Bean
    public EventService eventService(EventRepository eventRepository) {
        return new EventServiceImpl(eventRepository);
    }

    @Bean
    public SubscriptionService subscriptionService(
            SubscriptionRepository subscriptionRepository,
            UserRepository userRepository,
            EventRepository eventRepository) {

        return new SubscriptionServiceImpl(
                subscriptionRepository,
                userRepository,
                eventRepository
        );
    }

    @Bean
    public BroadcastService broadcastService(
            BroadcastLogRepository logRepository,
            SubscriptionRepository subscriptionRepository,
            EventUpdateRepository updateRepository) {

        return new BroadcastServiceImpl(
                logRepository,
                subscriptionRepository,
                updateRepository
        );
    }

    @Bean
    public EventUpdateService eventUpdateService(
            EventUpdateRepository updateRepository,
            EventRepository eventRepository,
            BroadcastService broadcastService) {

        return new EventUpdateServiceImpl(
                updateRepository,
                eventRepository,
                broadcastService
        );
    }
}
