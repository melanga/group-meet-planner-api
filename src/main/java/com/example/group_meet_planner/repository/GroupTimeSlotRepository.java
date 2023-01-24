package com.example.group_meet_planner.repository;

import com.example.group_meet_planner.entity.GroupTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupTimeSlotRepository extends JpaRepository<GroupTimeSlot, Long> {
    GroupTimeSlot findGroupTimeSlotByGroupId(String groupId);
}
