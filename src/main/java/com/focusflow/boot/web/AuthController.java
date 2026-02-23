package com.focusflow.boot.web;

import com.focusflow.boot.web.dto.UserResponse;
import com.focusflow.domain.model.User;
import com.focusflow.domain.port.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

// handles user identity stuff - links Clerk auth to our local db and returns user info
@RestController
@RequestMapping("/api/user")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    } 

    // returns current user info, creates a local record if it's their first time
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        String clerkId = jwt.getSubject();
        
        // try to find them in our db, otherwise create a new user entry
        User user = userRepository.findByClerkId(clerkId)
                .orElseGet(() -> {
                    // first login - grab whatever we can from the token
                    String username = jwt.getClaimAsString("email");
                    if (username == null) username = jwt.getClaimAsString("preferred_username");
                    if (username == null) username = clerkId;
                    
                    User newUser = User.onboard(clerkId, username, "USER");
                    return userRepository.save(newUser);
                });

        return ResponseEntity.ok(new UserResponse(
                user.getId().toString(),
                user.getUsername(),
                user.getRole()
        ));
    }

    
}
