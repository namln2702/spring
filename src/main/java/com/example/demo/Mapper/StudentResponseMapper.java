package com.example.demo.Mapper;

import com.example.demo.DTO.Response.StudentResponse;
import com.example.demo.Model.Student;
import org.springframework.stereotype.Component;


public interface StudentResponseMapper {
    StudentResponse toStudenReponse(Student student);
}
