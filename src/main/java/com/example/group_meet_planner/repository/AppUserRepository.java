package com.example.group_meet_planner.repository;

import com.example.group_meet_planner.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);

    boolean existsByUsername(String username);
}
