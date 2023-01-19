package com.example.group_meet_planner.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
public class TimeSlotId implements Serializable {
    private String user_username;
    private String group_id;

    public TimeSlotId(String user_username, String group_id) {
        this.user_username = user_username;
        this.group_id = group_id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(user_username, group_id);
    }
}
