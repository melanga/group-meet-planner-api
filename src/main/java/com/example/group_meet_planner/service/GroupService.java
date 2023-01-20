package com.example.group_meet_planner.service;

import com.example.group_meet_planner.dto.GroupDTO;
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

    public GroupDTO createGroup(Group group) {
        Group createdGroup = groupRepository.save(group);
        if (createdGroup.getId() == null)
            return null;
        return new GroupDTO(groupRepository.save(group));
    }

    public GroupDTO getGroup(String id) {
        if (groupRepository.existsById(id)) {
            return new GroupDTO(groupRepository.findGroupById(id));
        } else {
            return null;
        }
    }

    public GroupDTO updateGroup(Group group) {
        Group updateGroup = groupRepository.findGroupById(group.getId());
        if (updateGroup != null) {
            updateGroup.setName(group.getName());
            updateGroup.setDescription(group.getDescription());
            return new GroupDTO(groupRepository.save(updateGroup));
        } else {
            return null;
        }
    }

    public String deleteGroup(String id) {
        Group group = groupRepository.findGroupById(id);
        if (group != null) {
            groupRepository.deleteById(id);
            return "Group Deleted Successfully";
        } else {
            return null;
        }
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
