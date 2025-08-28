// adapters/memory/InMemoryBlockRepository.java
package com.focusflow.adapters.memory;

import com.focusflow.domain.model.TimeBlock;
import com.focusflow.domain.port.BlockRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/* Simulates adapter in memory, but not useful at all */

public class InMemoryBlockRepository implements BlockRepository {
    private final Map<UUID, TimeBlock> store = new ConcurrentHashMap<>();

    @Override
    public TimeBlock save(TimeBlock block) {
        store.put(block.getId(), block);
        return block;
    }

    @Override
    public Optional<TimeBlock> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<TimeBlock> findByUserId(String userId) {
        return store.values().stream()
                .filter(b -> b.getUserId().equals(userId))
                .toList();
    }
}
