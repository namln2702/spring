package com.example.demo.Mapper;

import com.example.demo.DTO.response.StudentResponse;
import com.example.demo.Model.Student;


public interface StudentResponseMapper {
    StudentResponse toStudenReponse(Student student);
}
