package com.focusflow.boot.web;

import com.focusflow.application.BlockApplicationService;
import com.focusflow.boot.web.dto.TimeBlockDto;

import org.springframework.web.bind.annotation.*;

import java.util.*;;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/blocks")
public class BlockController {
    private final BlockApplicationService app;

    public BlockController(BlockApplicationService app) {
        this.app = app;
    }

    @GetMapping
    public List<TimeBlockDto> listBlocks(@RequestParam String userId) {
        return app.listBlocksForUser(userId);
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

    @DeleteMapping("/{id}")
    public void deleteBlockForUser(@PathVariable UUID id) {
        app.deleteBlockForUser(id);
    }

}
