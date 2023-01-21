package com.example.group_meet_planner.repository;

import com.example.group_meet_planner.entity.TimeSlot;
import com.example.group_meet_planner.entity.TimeSlotId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, TimeSlotId> {
}
