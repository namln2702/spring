package com.example.demo.mapper;

import com.example.demo.dto.response.StudentResponse;
import com.example.demo.model.Student;


public interface StudentResponseMapper {
    StudentResponse toStudenReponse(Student student);
}
