package com.focusflow.boot.web.dto;

import java.time.Instant;
import java.util.UUID;
import com.focusflow.domain.model.TimeBlock;
import com.focusflow.domain.model.TimeBlock.Status;

public record TimeBlockDto(
    UUID id,
    String userId,
    String title,
    String category,
    int durationMinutes,
    int breakCount,
    int breakDuration,
    Instant scheduledStart,
    Status status
){
    public static TimeBlockDto fromDomain(TimeBlock block) {
        return new TimeBlockDto(
            block.getId(),
            block.getUserId(),
            block.getTitle(),
            block.getCategory(),
            block.getDurationMinutes(),
            block.getBreakCount(),
            block.getBreakDuration(),
            block.getScheduledStart(),
            block.getStatus()
        );
    }
}
