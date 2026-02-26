package com.focusflow.application;

import com.focusflow.domain.model.TimeBlock;
import com.focusflow.domain.port.BlockRepository;
import com.focusflow.domain.port.ActiveBlockStore;
import com.focusflow.domain.port.EventPublisher;
import com.focusflow.domain.event.BlockStarted;
import com.focusflow.adapters.jpa.entity.TimeBlockEntity;
import com.focusflow.adapters.jpa.mapper.TimeBlockMapper;
import com.focusflow.boot.web.dto.TimeBlockDto;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class BlockApplicationService {
    private final BlockRepository repo;
    private final ActiveBlockStore active;
    private final EventPublisher events;

    public BlockApplicationService(BlockRepository repo, ActiveBlockStore active, EventPublisher events) {
        this.repo = repo;
        this.active = active;
        this.events = events;
    }

    public UUID create(String userId, com.focusflow.boot.web.dto.CreateTimeBlockRequest request) {
        TimeBlock block = new TimeBlock(UUID.randomUUID(), userId, request.title(), request.durationMinutes());
        block.setCategory(request.category());
        block.setBreakCount(request.breakCount());
        block.setBreakDuration(request.breakDuration());
        block.setScheduledStart(request.scheduledStart());
        repo.save(block);
        return block.getId();
    }

    public void start(UUID id, String userId) {
        TimeBlock block = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Block not found: " + id));
        block.start();
        repo.update(block);
        active.acquireLock(userId, block.getId(), java.time.Duration.ofMinutes(block.getDurationMinutes()));
        //events.publish(new BlockStarted(block.getId(), userId));
    }

    public void complete(UUID id, String userId) {
        TimeBlock block = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Block not found: " + id));
        block.complete();
        repo.update(block);
        active.releaseLock(userId);
        // qui events.publish(new BlockCompleted(...));
    }

    public List<TimeBlockDto> listBlocksForUser(String userId) {
        return repo.findByUserId(userId).stream().map(TimeBlockDto::fromDomain).toList();
    }

    public void deleteBlockForUser(UUID id, String userId) {
        repo.findById(id).ifPresent(block -> {
            if (!block.getUserId().equals(userId)) {
                throw new IllegalArgumentException("Block does not belong to user");
            }
            repo.delete(block);
        });
    }
}
