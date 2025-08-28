package com.focusflow.domain.port;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

public interface ActiveBlockStore {
    boolean acquireLock(String userId, UUID blockId, Duration duration);
    void releaseLock(String userId);
    Optional<UUID> getActiveBlock(String userId);
}
