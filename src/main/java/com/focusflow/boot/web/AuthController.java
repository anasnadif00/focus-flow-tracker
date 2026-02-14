package com.focusflow.boot.web;

import com.focusflow.boot.config.JwtService;
import com.focusflow.boot.web.dto.LoginRequest;
import com.focusflow.boot.web.dto.LoginResponse;
import com.focusflow.domain.model.User;
import com.focusflow.domain.port.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Find user by username
        User user = userRepository.findByUsername(request.username())
                .orElse(null);

        // Check if user exists and password matches
        if (user == null || !passwordEncoder.matches(request.password(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }

        // Generate JWT token
        String token = jwtService.generateToken(user.getId().toString(), user.getRole());

        // Return token and user info
        return ResponseEntity.ok(new LoginResponse(
                token,
                user.getId().toString(),
                user.getUsername()
        ));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token provided");
        }

        String token = authHeader.substring(7);
        
        if (!jwtService.isValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        String userId = jwtService.extractUserId(token);
        
        User user = userRepository.findById(java.util.UUID.fromString(userId))
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.ok(new LoginResponse(
                null, // Don't send token again
                user.getId().toString(),
                user.getUsername()
        ));
    }
}
