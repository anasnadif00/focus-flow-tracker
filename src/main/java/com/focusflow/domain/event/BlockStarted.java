package com.focusflow.domain.event;

import java.util.UUID;

public class BlockStarted extends DomainEvent {
    private final UUID blockId;
    private final String userId;

    public BlockStarted(UUID blockId, String userId) {
        this.blockId = blockId;
        this.userId = userId;
    }

    public UUID getBlockId() { return blockId; }
    public String getUserId() { return userId; }
}
