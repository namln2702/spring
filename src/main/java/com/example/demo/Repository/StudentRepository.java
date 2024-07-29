package com.example.demo.Repository;

import com.example.demo.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("select u from Student u where u.name = ?1")
    Optional<Student> findByName(String name);
}
