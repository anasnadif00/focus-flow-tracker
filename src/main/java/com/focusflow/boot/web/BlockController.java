package com.focusflow.boot.web;

import com.focusflow.application.BlockApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/blocks")
public class BlockController {
    private final BlockApplicationService app;

    public BlockController(BlockApplicationService app) {
        this.app = app;
    }

    @PostMapping
    public UUID create(@RequestParam String userId,
                       @RequestParam String title,
                       @RequestParam int durationMinutes) {
        return app.create(userId, title, durationMinutes);
    }

    @PostMapping("/{id}/start")
    public void start(@PathVariable UUID id, @RequestParam String userId) {
        app.start(id, userId);
    }

    @PostMapping("/{id}/complete")
    public void complete(@PathVariable UUID id, @RequestParam String userId) {
        app.complete(id, userId);
    }
}
