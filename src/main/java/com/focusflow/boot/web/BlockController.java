package com.focusflow.boot.web;

import com.focusflow.application.BlockApplicationService;
import com.focusflow.boot.web.dto.TimeBlockDto;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/blocks")
public class BlockController {
    private final BlockApplicationService app;

    public BlockController(BlockApplicationService app) {
        this.app = app;
    }

    private String getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }

    @GetMapping
    public List<TimeBlockDto> listBlocks() {
        String userId = getAuthenticatedUserId();
        return app.listBlocksForUser(userId);
    }

    @PostMapping
    public UUID create(@RequestParam String title,
                       @RequestParam int durationMinutes) {
        String userId = getAuthenticatedUserId();
        return app.create(userId, title, durationMinutes);
    }

    @PostMapping("/{id}/start")
    public void start(@PathVariable UUID id) {
        String userId = getAuthenticatedUserId();
        app.start(id, userId);
    }

    @PostMapping("/{id}/complete")
    public void complete(@PathVariable UUID id) {
        String userId = getAuthenticatedUserId();
        app.complete(id, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteBlockForUser(@PathVariable UUID id) {
        app.deleteBlockForUser(id);
    }

}
