package com.example.group_meet_planner.dto;

import com.example.group_meet_planner.entity.TimeSlot;
import com.example.group_meet_planner.entity.TimeSlotId;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class TimeSlotDTO {
    private TimeSlotId id;
    private Timestamp start_time;
    private Timestamp end_time;
    private String createdBy;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public TimeSlotDTO(TimeSlot timeSlot) {
        this.id = timeSlot.getId();
        this.start_time = timeSlot.getStartTime();
        this.end_time = timeSlot.getEndTime();
        this.createdBy = timeSlot.getCreatedBy().getUsername();
        this.created_at = timeSlot.getCreatedAt();
        this.updated_at = timeSlot.getUpdatedAt();
    }
}
