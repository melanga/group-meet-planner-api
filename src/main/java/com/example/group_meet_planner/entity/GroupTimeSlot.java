package com.example.group_meet_planner.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class GroupTimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private Group group;
    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;

    public GroupTimeSlot(Group group, LocalDateTime startTime, LocalDateTime endTime) {
        this.group = group;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
