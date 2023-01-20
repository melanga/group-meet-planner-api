package com.example.group_meet_planner.controller;

import com.example.group_meet_planner.entity.AppUser;
import com.example.group_meet_planner.entity.Group;
import com.example.group_meet_planner.service.AppUserService;
import com.example.group_meet_planner.service.GroupService;
import com.example.group_meet_planner.util.ResponseEntityBuilder;
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
    public ResponseEntity<Object> createGroup(@RequestBody Group group, Principal principal) {
        AppUser createdBy = appUserService.findByUsername(principal.getName());
        group.setCreatedBy(createdBy);
        return ResponseEntityBuilder.builder()
                .body(groupService.createGroup(group))
                .errorMessage("Group creation failed").build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getGroup(@PathVariable String id) {
        return ResponseEntityBuilder.builder()
                .body(groupService.getGroup(id))
                .errorMessage("Group not found")
                .build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateGroup(@RequestBody Group group, @PathVariable String id) {
        group.setId(id);
        return ResponseEntityBuilder.builder()
                .body(groupService.updateGroup(group))
                .errorMessage("Group not found")
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteGroup(@PathVariable String id) {
        System.out.println("delete");
        return ResponseEntityBuilder.builder()
                .body(groupService.deleteGroup(id))
                .errorMessage("Group not found")
                .build();
    }

    @GetMapping("/owned/{username}")
    public ResponseEntity<Object> getOwnedGroups(@PathVariable String username) {
        return ResponseEntityBuilder.builder()
                .body(groupService.getOwnedGroups(username))
                .errorMessage("User not found")
                .build();
    }

    @GetMapping("/joined/{username}")
    public ResponseEntity<Object> getJoinedGroups(@PathVariable String username) {
        return ResponseEntityBuilder.builder()
                .body(groupService.getJoinedGroups(username))
                .errorMessage("User not found")
                .build();
    }

    @PostMapping("/{groupId}/add/{username}")
    public ResponseEntity<Object> addUserToGroup(@PathVariable String groupId, @PathVariable String username) {
        return ResponseEntityBuilder.builder()
                .body(groupService.addUserToGroup(groupId, username))
                .errorMessage("User not found")
                .build();
    }

    @PostMapping("/{groupId}/remove/{username}")
    public ResponseEntity<Object> removeUserFromGroup(@PathVariable String groupId, @PathVariable String username) {
        return ResponseEntityBuilder.builder()
                .body(groupService.removeUserFromGroup(groupId, username))
                .errorMessage("User not found")
                .build();
    }
}
