package com.example.group_meet_planner.dto;

import com.example.group_meet_planner.entity.GroupTimeSlot;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GroupTimeSlotDTO {
    private Long id;
    private String groupId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public GroupTimeSlotDTO(GroupTimeSlot groupTimeSlot) {
        this.id = groupTimeSlot.getId();
        this.groupId = groupTimeSlot.getGroup().getId();
        this.startTime = groupTimeSlot.getStartTime();
        this.endTime = groupTimeSlot.getEndTime();
    }
}
