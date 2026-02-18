package com.focusflow.boot.config;

import com.focusflow.domain.model.User;
import com.focusflow.domain.port.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initTestUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if test user already exists
            if (userRepository.findByUsername("testuser@magia3.it").isEmpty()) {
                // Create test user with password "password"
                User testUser = new User("testuser@magia3.it", passwordEncoder.encode("password"));
                userRepository.save(testUser);
                System.out.println("✅ Test user created: username=testuser@magia3.it, password=password");
            }
        };
    }
}
