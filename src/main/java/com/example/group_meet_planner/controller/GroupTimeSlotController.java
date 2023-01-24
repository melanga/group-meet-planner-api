package com.example.group_meet_planner.controller;

import com.example.group_meet_planner.service.GroupTimeSlotService;
import com.example.group_meet_planner.util.ResponseEntityBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/groupTimeSlot")
public class GroupTimeSlotController {
    private final GroupTimeSlotService groupTimeSlotService;

    public GroupTimeSlotController(GroupTimeSlotService groupTimeSlotService) {
        this.groupTimeSlotService = groupTimeSlotService;
    }

    @GetMapping("/get/{groupId}")
    public ResponseEntity<Object> getGroupTimeSlot(@PathVariable String groupId) {
        return ResponseEntityBuilder.builder()
                .body(groupTimeSlotService.get(groupId))
                .errorMessage("Group not found")
                .build();
    }

    @GetMapping("/calculate/{groupId}")
    public ResponseEntity<Object> calculateTimeSlot(@PathVariable String groupId) {
        return ResponseEntityBuilder.builder()
                .body(groupTimeSlotService.calculateTimeSlot(groupId))
                .errorMessage("Error calculating time slot")
                .build();
    }
}
