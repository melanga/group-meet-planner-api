package com.example.group_meet_planner.service;

import com.example.group_meet_planner.entity.AppUser;
import com.example.group_meet_planner.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
