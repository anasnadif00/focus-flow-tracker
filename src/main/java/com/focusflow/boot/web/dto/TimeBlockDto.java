package com.focusflow.boot.web.dto;

import java.util.UUID;
import com.focusflow.domain.model.TimeBlock;
import com.focusflow.domain.model.TimeBlock.Status;

public record TimeBlockDto(
    UUID id,
    String userId,
    String title,
    int durationMinutes,
    Status status
){
    public static TimeBlockDto fromDomain(TimeBlock block) {
        return new TimeBlockDto(
            block.getId(),
            block.getUserId(),
            block.getTitle(),
            block.getDurationMinutes(),
            block.getStatus()
        );
    }
}
