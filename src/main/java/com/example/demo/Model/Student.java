package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public Student(String name, Integer id, String email) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public Student() {

    }
}