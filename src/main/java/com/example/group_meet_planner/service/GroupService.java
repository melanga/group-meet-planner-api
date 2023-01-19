package com.example.group_meet_planner.service;

import com.example.group_meet_planner.dto.GroupDTO;
import com.example.group_meet_planner.entity.Group;
import com.example.group_meet_planner.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public GroupDTO createGroup(Group group) {
        return new GroupDTO(groupRepository.save(group));
    }

    public GroupDTO getGroup(String id) {
        return new GroupDTO(groupRepository.findGroupById(id));
    }

    public GroupDTO updateGroup(Group group) {
        return new GroupDTO(groupRepository.save(group));
    }

    public void deleteGroup(String id) {
        groupRepository.deleteById(id);
    }

    public List<GroupDTO> getOwnedGroups(String username) {
        var ownedGroups = groupRepository.findGroupsByCreatedByUsername(username);
        if (ownedGroups != null) {
            return ownedGroups.stream().map(GroupDTO::new).toList();
        }
        return null;
    }

    public List<GroupDTO> getJoinedGroups(String username) {
        var joinedGroups = groupRepository.findGroupsByMembersUsername(username);
        if (joinedGroups != null) {
            return joinedGroups.stream().map(GroupDTO::new).toList();
        }
        return null;
    }
}
