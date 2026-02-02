package com.focusflow.adapters.jpa.mapper;

import com.focusflow.adapters.jpa.entity.TimeBlockEntity;
import com.focusflow.adapters.jpa.entity.TimeBlockEntity.Status;
import com.focusflow.domain.model.TimeBlock;

public final class TimeBlockMapper {
    private TimeBlockMapper() {}

    public static TimeBlockEntity toEntity(TimeBlock d) {
        TimeBlockEntity e = new TimeBlockEntity();
        e.setId(d.getId());
        e.setUserId(d.getUserId());
        e.setTitle(d.getTitle());
        e.setDurationMinutes(d.getDurationMinutes());
        e.setScheduledStart(d.getScheduledStart());
        e.setScheduledEnd(d.getScheduledEnd());
        e.setActualStart(d.getActualStart());
        e.setActualEnd(d.getActualEnd());
        e.setStatus(TimeBlockEntity.Status.valueOf(d.getStatus().name()));
        return e;
    }

    public static TimeBlock toDomain(TimeBlockEntity e) {
        return TimeBlock.rehydrate(
            e.getId(),
            e.getUserId(),
            e.getTitle(),
            e.getDurationMinutes(),
            e.getScheduledStart(),
            e.getScheduledEnd(),
            e.getActualStart(),
            e.getActualEnd(),
            TimeBlock.Status.valueOf(e.getStatus().name())
        );
    }

    public static void updateEntity(TimeBlockEntity e, TimeBlock d) {
        e.setTitle(d.getTitle());
        e.setDurationMinutes(d.getDurationMinutes());
        e.setScheduledStart(d.getScheduledStart());
        e.setScheduledEnd(d.getScheduledEnd());
        e.setActualStart(d.getActualStart());
        e.setActualEnd(d.getActualEnd());
        e.setStatus(TimeBlockEntity.Status.valueOf(d.getStatus().name()));
    }
}
