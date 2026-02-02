
package com.focusflow.adapters.jpa;

import com.focusflow.domain.model.TimeBlock;
import com.focusflow.domain.port.BlockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Testcontainers
@Import(com.focusflow.TestcontainersConfiguration.class)
class TimeBlockJpaIntegrationTest {

    @Autowired
    private BlockRepository repo;

    @Test
    void should_save_and_load_timeblock_correctly() {
        UUID id = UUID.randomUUID();
        TimeBlock block = new TimeBlock(id, "userA", "Work Session", 25);

        repo.save(block);

        TimeBlock loaded = repo.findById(id).orElseThrow();

        assertThat(loaded.getId()).isEqualTo(id);
        assertThat(loaded.getUserId()).isEqualTo("userA");
        assertThat(loaded.getTitle()).isEqualTo("Work Session");
        assertThat(loaded.getDurationMinutes()).isEqualTo(25);
        assertThat(loaded.getStatus()).isEqualTo(TimeBlock.Status.SCHEDULED);
    }

    @Test
    void should_find_blocks_by_user_id() {
        TimeBlock b1 = new TimeBlock(UUID.randomUUID(), "alice", "Deep Work", 30);
        TimeBlock b2 = new TimeBlock(UUID.randomUUID(), "alice", "Break", 10);
        TimeBlock b3 = new TimeBlock(UUID.randomUUID(), "bob",   "Workout", 20);

        repo.save(b1);
        repo.save(b2);
        repo.save(b3);

        List<TimeBlock> result = repo.findByUserId("alice");

        assertThat(result)
                .hasSize(2)
                .extracting(TimeBlock::getTitle)
                .containsExactlyInAnyOrder("Deep Work", "Break");
    }

    @Test
    void status_enum_should_persist_and_load_correctly() {
        TimeBlock block = new TimeBlock(UUID.randomUUID(), "userX", "Enum Test", 10);
        block.start(); // RUNNING

        repo.save(block);

        TimeBlock loaded = repo.findById(block.getId()).orElseThrow();

        assertThat(loaded.getStatus()).isEqualTo(TimeBlock.Status.RUNNING);
        assertThat(loaded.getActualStart()).isNotNull();
    }

    @Test
    void optimistic_locking_should_fail_on_concurrent_updates() {
        TimeBlock original = new TimeBlock(UUID.randomUUID(), "userX", "Lock Test", 10);
        repo.save(original);

        // load two separate instances simulating two concurrent requests
        TimeBlock a = repo.findById(original.getId()).orElseThrow();
        TimeBlock b = repo.findById(original.getId()).orElseThrow();

        // modify instance A
        a.start();
        repo.save(a);

        // modify instance B → should fail because version changed
        b.start();

        assertThatThrownBy(() -> repo.save(b))
                .isInstanceOf(org.springframework.orm.ObjectOptimisticLockingFailureException.class);
    }
}
