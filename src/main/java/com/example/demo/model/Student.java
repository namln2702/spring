package com.example.demo.model;

import com.example.demo.validator.DobConstraint;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String name;

    private String username;

    private String password;


    private boolean isDeleted = false;


    private int age;
    @ManyToMany
    public  Set<Role> roles;


}