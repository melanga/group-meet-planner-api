package com.example.group_meet_planner.controller;

import com.example.group_meet_planner.entity.AppUser;
import com.example.group_meet_planner.entity.Group;
import com.example.group_meet_planner.service.AppUserService;
import com.example.group_meet_planner.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/group")
@AllArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final AppUserService appUserService;

    @PostMapping("/create")
    public Group createGroup(@RequestBody Group group, Principal principal) {
        AppUser createdBy = appUserService.findByUsername(principal.getName());
        group.setCreatedBy(createdBy);
        return groupService.createGroup(group);
    }

    @GetMapping("/get/{id}")
    public Group getGroup(@PathVariable String id) {
        return groupService.getGroup(id);
    }

    @PutMapping("/update/{id}")
    public Group updateGroup(@RequestBody Group group, @PathVariable String id) {
        group.setId(id);
        return groupService.updateGroup(group);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable String id) {
        System.out.println("delete");
        try {
            groupService.deleteGroup(id);
            return ResponseEntity.ok("Group deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Group not found");
        }
    }

    @GetMapping("/owned/{username}")
    public Object getOwnedGroups(@PathVariable String username) {
        return groupService.getOwnedGroups(username);
    }

    @GetMapping("/joined/{username}")
    public Object getJoinedGroups(@PathVariable String username) {
        return groupService.getJoinedGroups(username);
    }
}
