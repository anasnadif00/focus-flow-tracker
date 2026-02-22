package com.focusflow.adapters.jpa;

import com.focusflow.domain.model.User;
import com.focusflow.domain.port.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryAdapter implements UserRepository {
    
    private final UserJpaRepository jpaRepository;
    
    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    
    @Override
    public Optional<User> findByUsername(String username) {
        return jpaRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByClerkId(String clerkId) {
        return jpaRepository.findByClerkId(clerkId);
    }
    
    @Override
    public @NonNull User save(@NonNull User user) {
        return jpaRepository.save(user);
    }
    
    @Override
    public Optional<User> findById(@NonNull UUID id) {
        return jpaRepository.findById(id);
    }
}
