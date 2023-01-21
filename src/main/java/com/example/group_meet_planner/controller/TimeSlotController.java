package com.example.group_meet_planner.controller;

import com.example.group_meet_planner.entity.TimeSlot;
import com.example.group_meet_planner.service.TimeSlotService;
import com.example.group_meet_planner.util.ResponseEntityBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/timeSlot")
@AllArgsConstructor
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    @PostMapping("/create/{username}/{groupId}")
    public ResponseEntity<Object> createTimeSlot(@RequestBody TimeSlot timeSlot, @PathVariable String groupId, @PathVariable String username) {
        return ResponseEntityBuilder.builder()
                .body(timeSlotService.create(timeSlot, username, groupId))
                .errorMessage("Error occurred while creating time slot")
                .build();
    }

    @GetMapping("/get/{username}/{groupId}")
    public ResponseEntity<Object> getTimeSlot(@PathVariable String groupId, @PathVariable String username) {
        return ResponseEntityBuilder.builder()
                .body(timeSlotService.get(username, groupId))
                .errorMessage("Error occurred while getting time slots")
                .build();
    }

    @PutMapping("/update/{username}/{groupId}")
    public ResponseEntity<Object> updateTimeSlot(@RequestBody TimeSlot timeSlot, @PathVariable String groupId, @PathVariable String username) {
        return ResponseEntityBuilder.builder()
                .body(timeSlotService.update(timeSlot, username, groupId))
                .errorMessage("Error occurred while updating time slot")
                .build();
    }

    @DeleteMapping("/delete/{username}/{groupId}")
    public ResponseEntity<Object> deleteTimeSlot(@PathVariable String groupId, @PathVariable String username) {
        return ResponseEntityBuilder.builder()
                .body(timeSlotService.delete(username, groupId))
                .errorMessage("Error occurred while deleting time slots")
                .build();
    }
}
