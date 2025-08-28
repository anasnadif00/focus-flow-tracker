package com.focusflow.domain.model;

import java.time.Instant;
import java.util.UUID;

public class TimeBlock {
    private final UUID id;
    private final String userId;
    private final String title;
    private final int durationMinutes;

    private Instant scheduledStart;
    private Instant scheduledEnd;
    private Instant actualStart;
    private Instant actualEnd;

    private Status status;

    public enum Status { SCHEDULED, RUNNING, COMPLETED, SKIPPED }

    public TimeBlock(UUID id, String userId, String title, int durationMinutes) {
<<<<<<< HEAD
        if (durationMinutes <= 0) throw new IllegalArgumentException("Duration must be > 0");
=======
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Duration must be > 0");
        }
>>>>>>> cba7fd92f7a79d089ff8f48b5f27bc8415d89e5c
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.status = Status.SCHEDULED;
    }

<<<<<<< HEAD
    public static TimeBlock rehydrate(UUID id, String userId, String title, int durationMinutes,
                                      Instant scheduledStart, Instant scheduledEnd,
                                      Instant actualStart, Instant actualEnd, Status status) {
        TimeBlock b = new TimeBlock(id, userId, title, durationMinutes);
        b.scheduledStart = scheduledStart;
        b.scheduledEnd = scheduledEnd;
        b.actualStart = actualStart;
        b.actualEnd = actualEnd;
        b.status = status != null ? status : Status.SCHEDULED;
        return b;
    }

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
=======
    public void start() {
        if (status != Status.SCHEDULED) {
            throw new IllegalStateException("Can only start from SCHEDULED");
        }
        this.status = Status.RUNNING;
        this.actualStart = Instant.now();
    }

    public void complete() {
        if (status != Status.RUNNING) {
            throw new IllegalStateException("Can only complete if RUNNING");
        }
        this.status = Status.COMPLETED;
        this.actualEnd = Instant.now();
    }

    public void skip() {
        if (status == Status.COMPLETED) {
            throw new IllegalStateException("Cannot skip a completed block");
        }
        this.status = Status.SKIPPED;
        this.actualEnd = Instant.now();
>>>>>>> cba7fd92f7a79d089ff8f48b5f27bc8415d89e5c
    }

    public UUID getId() { return id; }
    public String getUserId() { return userId; }
    public String getTitle() { return title; }
    public int getDurationMinutes() { return durationMinutes; }
<<<<<<< HEAD
    public Instant getScheduledStart() { return scheduledStart; }
    public Instant getScheduledEnd() { return scheduledEnd; }
    public Instant getActualStart() { return actualStart; }
    public Instant getActualEnd() { return actualEnd; }
=======
>>>>>>> cba7fd92f7a79d089ff8f48b5f27bc8415d89e5c
    public Status getStatus() { return status; }
}
