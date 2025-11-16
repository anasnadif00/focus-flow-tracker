package com.focusflow.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class TimeBlockTest {
    @Test
    void start_sets_status_to_running_and_sets_actual_start() {
        TimeBlock block = new TimeBlock(UUID.randomUUID(), "u1", "study", 30);

        block.start();

        assertThat(block.getStatus()).isEqualTo(TimeBlock.Status.RUNNING);
        assertThat(block.getActualStart()).isNotNull();
    }
}
