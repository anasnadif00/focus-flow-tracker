package com.focusflow.boot.web;

import com.focusflow.application.BlockApplicationService;
import com.focusflow.boot.web.dto.TimeBlockDto;
import com.focusflow.domain.model.User;
import com.focusflow.domain.port.UserRepository;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/blocks")
public class BlockController {
    private final BlockApplicationService app;
    private final UserRepository userRepository;

    public BlockController(BlockApplicationService app, UserRepository userRepository) {
        this.app = app;
        this.userRepository = userRepository;
    }

    // maps the clerk token to our internal user id
    private String getLocalUserId(Jwt jwt) {
        return userRepository.findByClerkId(jwt.getSubject())
                .map(User::getId)
                .map(UUID::toString)
                .orElseThrow(() -> new RuntimeException("User not synced - hit /api/user/me first"));
    }

    @GetMapping
    public List<TimeBlockDto> listBlocks(@AuthenticationPrincipal Jwt jwt) {
        String userId = getLocalUserId(jwt);
        return app.listBlocksForUser(userId);
    }

    @PostMapping
    public UUID create(@AuthenticationPrincipal Jwt jwt,
                       @RequestParam String title,
                       @RequestParam int durationMinutes) {
        String userId = getLocalUserId(jwt);
        return app.create(userId, title, durationMinutes);
    }

    @PostMapping("/{id}/start")
    public void start(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID id) {
        String userId = getLocalUserId(jwt);
        app.start(id, userId);
    }

    @PostMapping("/{id}/complete")
    public void complete(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID id) {
        String userId = getLocalUserId(jwt);
        app.complete(id, userId);
    }

    // only lets you delete your own blocks
    @DeleteMapping("/{id}")
    public void deleteBlockForUser(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID id) {
        String userId = getLocalUserId(jwt);
        app.deleteBlockForUser(id, userId);
    }
}
