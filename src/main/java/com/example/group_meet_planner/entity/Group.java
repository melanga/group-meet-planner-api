package com.example.group_meet_planner.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "groups")
public class Group extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(columnDefinition = "VARCHAR(64) DEFAULT 'Untitled'")
    private String name;
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'Undefined'")
    private String description;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private AppUser createdBy;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<AppUser> members;
}
