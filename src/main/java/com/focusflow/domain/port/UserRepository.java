package com.focusflow.domain.port;

import com.focusflow.domain.model.User;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    @NonNull User save(@NonNull User user);
    Optional<User> findById(@NonNull UUID id);
}
