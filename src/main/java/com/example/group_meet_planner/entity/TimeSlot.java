package com.example.group_meet_planner.entity;

import com.example.group_meet_planner.entity.abstracts.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "time_slots")
public class TimeSlot extends Auditable {
    @EmbeddedId
    private TimeSlotId id;
    @ManyToOne
    @MapsId("user_username")
    @JoinColumn(name = "created_by", updatable = false, nullable = false)
    private AppUser createdBy;
    @ManyToOne
    @MapsId("group_id")
    private Group group;
    @NotBlank
    private Timestamp startTime;
    @NotBlank
    private Timestamp endTime;
}
