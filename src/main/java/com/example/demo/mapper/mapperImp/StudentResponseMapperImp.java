package com.example.demo.mapper.mapperImp;

import com.example.demo.dto.response.StudentResponse;
import com.example.demo.mapper.StudentResponseMapper;
import com.example.demo.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentResponseMapperImp implements StudentResponseMapper {
    @Override
    public StudentResponse toStudenReponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();

        if (student == null){
            return null;
        }

        studentResponse.setName(student.getName());
        studentResponse.setUsername(student.getUsername());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setRole(student.getRole());

        return studentResponse;
    }
}
