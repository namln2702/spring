package com.example.demo.Mapper;


import com.example.demo.DTO.request.StudentRequest;
import com.example.demo.Model.Student;
import org.springframework.stereotype.Component;


@Component
public interface StudentMapper {
    Student toStudent(StudentRequest studentRequest);

}
