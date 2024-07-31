package com.example.demo.mapper;


import com.example.demo.dto.Request.StudentRequest;
import com.example.demo.model.Student;
import org.springframework.stereotype.Component;


@Component
public interface StudentMapper {
    Student toStudent(StudentRequest studentRequest);

}
