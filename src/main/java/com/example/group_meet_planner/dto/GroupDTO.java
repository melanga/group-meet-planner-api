package com.example.group_meet_planner.dto;

import com.example.group_meet_planner.entity.Group;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class GroupDTO {
    private String id;
    private String name;
    private String description;
    private UserDTO createdBy;
    private List<UserDTO> members;
    private List<TimeSlotDTO> timeSlots;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public GroupDTO(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.description = group.getDescription();
        this.createdBy = new UserDTO(group.getCreatedBy());
        this.members = Optional.ofNullable(group.getMembers()).stream().flatMap(Collection::stream).map(UserDTO::new).collect(Collectors.toList());
        this.timeSlots = Optional.ofNullable(group.getTimeSlots()).stream().flatMap(Collection::stream).map(TimeSlotDTO::new).collect(Collectors.toList());
        this.created_at = group.getCreatedAt();
        this.updated_at = group.getUpdatedAt();
    }
}
