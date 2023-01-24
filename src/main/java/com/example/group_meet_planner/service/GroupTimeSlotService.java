package com.example.group_meet_planner.service;

import com.example.group_meet_planner.dto.GroupTimeSlotDTO;
import com.example.group_meet_planner.entity.Group;
import com.example.group_meet_planner.entity.GroupTimeSlot;
import com.example.group_meet_planner.entity.TimeSlot;
import com.example.group_meet_planner.repository.GroupRepository;
import com.example.group_meet_planner.repository.GroupTimeSlotRepository;
import com.example.group_meet_planner.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class GroupTimeSlotService {
    private final TimeSlotRepository timeSlotRepository;
    private final GroupTimeSlotRepository groupTimeSlotRepository;
    private final GroupRepository groupRepository;

    public GroupTimeSlotService(TimeSlotRepository timeSlotRepository, GroupTimeSlotRepository groupTimeSlotRepository, GroupRepository groupRepository) {
        this.timeSlotRepository = timeSlotRepository;
        this.groupTimeSlotRepository = groupTimeSlotRepository;
        this.groupRepository = groupRepository;
    }

    public GroupTimeSlotDTO get(String groupId) {
        return new GroupTimeSlotDTO(groupTimeSlotRepository.findGroupTimeSlotByGroupId(groupId));
    }

    public GroupTimeSlot calculateTimeSlot(String groupId) {
        List<TimeSlot> timeSlots = timeSlotRepository.findTimeSlotsByGroupId(groupId);
        int size = timeSlots.size();
        if (size != 0) {
            GroupTimeSlot commonTimeSlot = new GroupTimeSlot();
            commonTimeSlot.setStartTime(timeSlots.get(0).getStartTime());
            commonTimeSlot.setEndTime(timeSlots.get(0).getEndTime());
            // edge case when there is only one time slot
            if (size == 1) {
                return commonTimeSlot;
            }
            Group group = groupRepository.findGroupById(groupId);
            for (int i = 1; i < timeSlots.size(); i++) {
                TimeSlot currentTimeSlot = timeSlots.get(i);
                // check if the current time slot is within the common time slot
                if (currentTimeSlot.getStartTime().isBefore(commonTimeSlot.getEndTime())) {
                    // then set the start time as the earliest start time
                    commonTimeSlot.setStartTime(commonTimeSlot.getStartTime().isAfter(currentTimeSlot.getStartTime()) ? commonTimeSlot.getStartTime() : currentTimeSlot.getStartTime());
                    // then set the end time as the furthest end time
                    commonTimeSlot.setEndTime(commonTimeSlot.getEndTime().isBefore(currentTimeSlot.getEndTime()) ? commonTimeSlot.getEndTime() : currentTimeSlot.getEndTime());
                }
            }
            commonTimeSlot.setGroup(group);
            return groupTimeSlotRepository.save(commonTimeSlot);
        }
        return null;
    }

    public GroupTimeSlot calculateTimeSlotGreedy(String groupId) {
        List<TimeSlot> timeSlots = timeSlotRepository.findTimeSlotsByGroupId(groupId);
        Group group = groupRepository.findGroupById(groupId);
        int size = timeSlots.size();
        if (size != 0) {
            timeSlots.sort(Comparator.comparing(TimeSlot::getStartTime));
            LocalDateTime startTime = timeSlots.get(0).getStartTime();
            LocalDateTime endTime = timeSlots.get(0).getEndTime();
            for (int i = 1; i < timeSlots.size(); i++) {
                TimeSlot currentTimeSlot = timeSlots.get(i);
                if (currentTimeSlot.getStartTime().isAfter(endTime)) {
                    return new GroupTimeSlot(group, startTime, endTime);
                }
                if (currentTimeSlot.getEndTime().isBefore(endTime)) {
                    endTime = currentTimeSlot.getEndTime();
                }
            }
            return new GroupTimeSlot(group, startTime, endTime);
        }
        return null;
    }
}
