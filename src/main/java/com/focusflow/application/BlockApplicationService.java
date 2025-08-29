package com.focusflow.application;

import com.focusflow.domain.model.TimeBlock;
import com.focusflow.domain.port.BlockRepository;
import com.focusflow.domain.port.ActiveBlockStore;
import com.focusflow.domain.port.EventPublisher;
import com.focusflow.domain.event.BlockStarted;


import java.util.UUID;

public class BlockApplicationService {
    private final BlockRepository repo;
    private final ActiveBlockStore active;
    private final EventPublisher events;

    public BlockApplicationService(BlockRepository repo, ActiveBlockStore active, EventPublisher events) {
        this.repo = repo;
        this.active = active;
        this.events = events;
    }

    public UUID create(String userId, String title, int durationMinutes) {
        TimeBlock block = new TimeBlock(UUID.randomUUID(), userId, title, durationMinutes);
        repo.save(block);
        return block.getId();
    }

    public void start(UUID id, String userId) {
        TimeBlock block = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Block not found: " + id));
        block.start();
        repo.save(block);
        active.acquireLock(userId, block.getId(), java.time.Duration.ofMinutes(block.getDurationMinutes()));
        events.publish(new BlockStarted(block.getId(), userId));
    }

    public void complete(UUID id, String userId) {
        TimeBlock block = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Block not found: " + id));
        block.complete();
        repo.save(block);
        active.releaseLock(userId);
        // qui events.publish(new BlockCompleted(...));
    }
}
