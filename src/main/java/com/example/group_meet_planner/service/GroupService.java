package com.example.group_meet_planner.service;

import com.example.group_meet_planner.entity.Group;
import com.example.group_meet_planner.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group getGroup(String id) {
        return groupRepository.findGroupById(id);
    }

    public Group updateGroup(Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(String id) {
        groupRepository.deleteById(id);
    }

    public List<Group> getOwnedGroups(String username) {
        return groupRepository.findGroupsByCreatedByUsername(username);
    }

    public List<Group> getJoinedGroups(String username) {
        return groupRepository.findGroupsByMembersUsername(username);
    }
}
