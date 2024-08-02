package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;




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

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "username", nullable = true)
    private String username;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;


    @Column(name = "role")
    private String role ;

}