package com.example.group_meet_planner.service;

import com.example.group_meet_planner.dto.TimeSlotDTO;
import com.example.group_meet_planner.entity.AppUser;
import com.example.group_meet_planner.entity.Group;
import com.example.group_meet_planner.entity.TimeSlot;
import com.example.group_meet_planner.entity.TimeSlotId;
import com.example.group_meet_planner.repository.AppUserRepository;
import com.example.group_meet_planner.repository.GroupRepository;
import com.example.group_meet_planner.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;
    private final GroupRepository groupRepository;
    private final AppUserRepository appUserRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository, GroupRepository groupRepository, AppUserRepository appUserRepository) {
        this.timeSlotRepository = timeSlotRepository;
        this.groupRepository = groupRepository;
        this.appUserRepository = appUserRepository;
    }

    public TimeSlotDTO create(TimeSlot timeSlot, String username, String groupId) {
        Group group = groupRepository.findGroupById(groupId);
        if (group != null) {
            AppUser appUser = appUserRepository.findByUsername(username);
            if (appUser != null) {
                TimeSlotId id = new TimeSlotId(username, groupId);
                timeSlot.setId(id);
                timeSlot.setCreatedBy(appUser);
                timeSlot.setGroup(group);
                TimeSlot savedTimeSlot = timeSlotRepository.save(timeSlot);
                if (savedTimeSlot.getId() != null) {
                    return new TimeSlotDTO(savedTimeSlot);
                }
            }
        }
        return null;
    }

    public TimeSlotDTO get(String username, String groupId) {
        TimeSlot timeSlot = timeSlotRepository.findById(new TimeSlotId(username, groupId)).orElse(null);
        if (timeSlot != null) {
            return new TimeSlotDTO(timeSlot);
        }
        return null;
    }

    public String delete(String username, String groupId) {
        TimeSlot timeSlot = timeSlotRepository.findById(new TimeSlotId(username, groupId)).orElse(null);
        if (timeSlot != null) {
            timeSlotRepository.delete(timeSlot);
            return "TimeSlot deleted successfully";
        }
        return null;
    }

    public TimeSlotDTO update(TimeSlot timeSlot, String username, String groupId) {
        System.out.println(username + " " + groupId);
        TimeSlot existingTimeSlot = timeSlotRepository.findById(new TimeSlotId(username, groupId)).orElse(null);
        if (existingTimeSlot != null) {
            existingTimeSlot.setStartTime(timeSlot.getStartTime());
            existingTimeSlot.setEndTime(timeSlot.getEndTime());
            return new TimeSlotDTO(timeSlotRepository.save(existingTimeSlot));
        }
        return null;
    }
}
