package com.focusflow.boot.web.dto;

import java.time.Instant;

public record CreateTimeBlockRequest(
    String title,
    String category,
    int durationMinutes,
    int breakCount,
    int breakDuration,
    Instant scheduledStart
) {}
