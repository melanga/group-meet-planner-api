package com.example.group_meet_planner.service;

import com.example.group_meet_planner.entity.AppUser;
import com.example.group_meet_planner.entity.Role;
import com.example.group_meet_planner.repository.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> register(AppUser appUser) {
        try {
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            appUser.setRole(Role.USER);
            appUserRepository.save(appUser);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User registration failed");
        }
    }
}
