package com.focusflow.adapters.redis;

import com.focusflow.domain.port.ActiveBlockStore;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Component
public class RedisActiveBlockStore implements ActiveBlockStore {

    private static final String KEY_PREFIX = "active:block:";

    private final StringRedisTemplate redis;

    public RedisActiveBlockStore(StringRedisTemplate redis) {
        this.redis = redis;
    }

    @Override
    public boolean acquireLock(String userId, UUID blockId, Duration duration) {
        String key = KEY_PREFIX + userId;
        // SET key value NX EX/ PX — in Spring: setIfAbsent(value, timeout) è atomico
        Boolean ok = redis.opsForValue().setIfAbsent(key, blockId.toString(), duration);
        return Boolean.TRUE.equals(ok);
    }

    @Override
    public void releaseLock(String userId) {
        String key = KEY_PREFIX + userId;
        redis.delete(key);
    }

    @Override
    public Optional<UUID> getActiveBlock(String userId) {
        String key = KEY_PREFIX + userId;
        String value = redis.opsForValue().get(key);
        if (value == null || value.isBlank()) return Optional.empty();
        try {
            return Optional.of(UUID.fromString(value));
        } catch (IllegalArgumentException ex) {
            // se qualcuno ha sporcato la chiave con altro formato…
            return Optional.empty();
        }
    }
}
