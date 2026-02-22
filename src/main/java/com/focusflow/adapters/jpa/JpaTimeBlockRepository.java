// adapters/jpa/JpaTimeBlockRepository.java
package com.focusflow.adapters.jpa;

import com.focusflow.adapters.jpa.entity.TimeBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaTimeBlockRepository extends JpaRepository<TimeBlockEntity, UUID> {
    List<TimeBlockEntity> findByUserId(UUID userId);
}
