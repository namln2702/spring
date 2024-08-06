package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity

public class Role {

    @Id
    private String name;
    private String description;
    private boolean isDelete = false;

    @ManyToMany
    Set<Permission> permissions;

}