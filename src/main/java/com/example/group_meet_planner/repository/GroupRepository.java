package com.example.group_meet_planner.repository;

import com.example.group_meet_planner.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, String> {
    Group findGroupById(String id);

    List<Group> findGroupsByCreatedByUsername(String username);

    List<Group> findGroupsByMembersUsername(String username);
}
