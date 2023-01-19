package com.example.group_meet_planner.entity;

import com.example.group_meet_planner.entity.abstracts.Auditable;
import com.example.group_meet_planner.entity.enums.Role;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "users")
public class AppUser extends Auditable {
    @Id
    private String username;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    @Email()
    private String email;
    private String password;
    @OneToMany(mappedBy = "createdBy")
    private List<TimeSlot> timeSlots;
    @OneToMany(mappedBy = "createdBy")
    private List<Group> createdGroups;
    @ManyToMany(mappedBy = "members")
    private List<Group> groups;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public String toString() {
        return "AppUser{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' + "}";
    }
}
