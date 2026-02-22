package com.focusflow.domain.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(unique = true)
    private String clerkId;
    
    @Column(unique = true)
    private String username;
    
    @Column
    private String password;
    
    @Column(nullable = false)
    private String role = "USER";
    
    protected User() {
        // JPA requires a no-arg constructor
    }

    // Static factory method for Clerk onboarding
    public static User onboard(String clerkId, String username, String role) {
        User user = new User();
        user.clerkId = clerkId;
        user.username = username;
        user.role = role;
        return user;
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "USER";
    }
    
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public UUID getId() {
        return id;
    }

    public String getClerkId() {
        return clerkId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
