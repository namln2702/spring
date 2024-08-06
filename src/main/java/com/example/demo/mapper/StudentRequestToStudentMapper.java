package com.example.demo.mapper;


import com.example.demo.dto.request.StudentRequest;
import com.example.demo.model.Student;
import org.springframework.stereotype.Component;


@Component
public interface StudentRequestToStudentMapper {
    Student toStudent(StudentRequest studentRequest);

}
