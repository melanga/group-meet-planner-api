package com.example.group_meet_planner.service;

import com.example.group_meet_planner.dto.GroupDTO;
import com.example.group_meet_planner.entity.AppUser;
import com.example.group_meet_planner.entity.Group;
import com.example.group_meet_planner.repository.AppUserRepository;
import com.example.group_meet_planner.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final AppUserRepository appUserRepository;

    public GroupService(GroupRepository groupRepository, AppUserRepository appUserRepository) {
        this.groupRepository = groupRepository;
        this.appUserRepository = appUserRepository;
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

    public String addUserToGroup(String groupId, String username) {
        Group group = groupRepository.findGroupById(groupId);
        AppUser user = appUserRepository.findByUsername(username);
        if (group != null && user != null) {
            if (group.getMembers().contains(user) || group.getCreatedBy().equals(user)) {
                return "User already in group";
            }
            group.getMembers().add(user);
            groupRepository.save(group);
            return "User '" + username + "' added to group successfully";
        } else {
            return null;
        }
    }

    public String removeUserFromGroup(String groupId, String username) {
        Group group = groupRepository.findGroupById(groupId);
        AppUser user = appUserRepository.findByUsername(username);
        if (group != null && user != null) {
            if (group.getCreatedBy().equals(user)) {
                return "Cannot remove owner of group";
            }
            group.getMembers().remove(user);
            groupRepository.save(group);
            return "User '" + username + "' removed from group successfully";
        } else {
            return null;
        }
    }
}
