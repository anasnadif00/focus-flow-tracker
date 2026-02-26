package com.focusflow.domain.model;

import java.time.Instant;
import java.util.UUID;

public class TimeBlock {
    private final UUID id;
    private final String userId;
    private final String title;
    private final int durationMinutes;

    private String category;
    private int breakCount;
    private int breakDuration;

    private Instant scheduledStart;
    private Instant scheduledEnd;
    private Instant actualStart;
    private Instant actualEnd;

    private Status status;

    public enum Status { SCHEDULED, RUNNING, COMPLETED, SKIPPED }

    public TimeBlock(UUID id, String userId, String title, int durationMinutes) {
        if (durationMinutes <= 0) throw new IllegalArgumentException("Duration must be > 0");
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.status = Status.SCHEDULED;
    }

    public static TimeBlock rehydrate(UUID id, String userId, String title, int durationMinutes,
                                      String category, int breakCount, int breakDuration,
                                      Instant scheduledStart, Instant scheduledEnd,
                                      Instant actualStart, Instant actualEnd, Status status) {
        TimeBlock b = new TimeBlock(id, userId, title, durationMinutes);
        b.category = category;
        b.breakCount = breakCount;
        b.breakDuration = breakDuration;
        b.scheduledStart = scheduledStart;
        b.scheduledEnd = scheduledEnd;
        b.actualStart = actualStart;
        b.actualEnd = actualEnd;
        b.status = status != null ? status : Status.SCHEDULED;
        return b;
    }

    public void setCategory(String category) { this.category = category; }
    public void setBreakCount(int breakCount) { this.breakCount = breakCount; }
    public void setBreakDuration(int breakDuration) { this.breakDuration = breakDuration; }
    public void setScheduledStart(Instant scheduledStart) { this.scheduledStart = scheduledStart; }
    public void setScheduledEnd(Instant scheduledEnd) { this.scheduledEnd = scheduledEnd; }

    public void start() {
        if (status != Status.SCHEDULED) throw new IllegalStateException("Can only start from SCHEDULED");
        status = Status.RUNNING;
        actualStart = Instant.now();
    }

    public void complete() {
        if (status != Status.RUNNING) throw new IllegalStateException("Can only complete if RUNNING");
        status = Status.COMPLETED;
        actualEnd = Instant.now();
    }

    public void skip() {
        if (status == Status.COMPLETED) throw new IllegalStateException("Cannot skip a completed block");
        status = Status.SKIPPED;
        actualEnd = Instant.now();
    }

    public UUID getId() { return id; }
    public String getUserId() { return userId; }
    public String getTitle() { return title; }
    public int getDurationMinutes() { return durationMinutes; }
    
    public String getCategory() { return category; }
    public int getBreakCount() { return breakCount; }
    public int getBreakDuration() { return breakDuration; }

    public Instant getScheduledStart() { return scheduledStart; }
    public Instant getScheduledEnd() { return scheduledEnd; }
    public Instant getActualStart() { return actualStart; }
    public Instant getActualEnd() { return actualEnd; }
    public Status getStatus() { return status; }
}
