package com.example.group_meet_planner.dto;

import com.example.group_meet_planner.entity.AppUser;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public UserDTO(AppUser appUser) {
        this.username = appUser.getUsername();
        this.firstName = appUser.getFirstName();
        this.lastName = appUser.getLastName();
        this.email = appUser.getEmail();
    }
}
