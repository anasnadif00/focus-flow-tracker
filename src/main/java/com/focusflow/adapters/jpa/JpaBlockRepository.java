// adapters/jpa/JpaBlockRepository.java
package com.focusflow.adapters.jpa;

import com.focusflow.adapters.jpa.entity.TimeBlockEntity;
import com.focusflow.adapters.jpa.mapper.TimeBlockMapper;
import com.focusflow.domain.model.TimeBlock;
import com.focusflow.domain.port.BlockRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import java.util.List;

@Repository
public class JpaBlockRepository implements BlockRepository {

    private final JpaTimeBlockRepository jpa;

    public JpaBlockRepository(JpaTimeBlockRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public TimeBlock save(TimeBlock block) {
        TimeBlockEntity entity = TimeBlockMapper.toEntity(block);
        TimeBlockEntity saved  = jpa.save(entity);
        return TimeBlockMapper.toDomain(saved);
    }

    @Override
    public void update(TimeBlock block) {
        TimeBlockEntity entity = jpa.findById(block.getId())
            .orElseThrow(() -> new IllegalStateException(
                "TimeBlock not found: " + block.getId()
            ));

        TimeBlockMapper.updateEntity(entity, block);
    }


    @Override
    public Optional<TimeBlock> findById(UUID id){
        return jpa.findById(id).map(TimeBlockMapper::toDomain);
    }

    @Override
    public List<TimeBlock> findByUserId(String userId) {
        return jpa.findByUserId(userId).stream().map(TimeBlockMapper::toDomain)
        .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void delete(TimeBlock block) {
        jpa.deleteById(block.getId());
    }

}
