package com.example.demo.Mapper.mapperImp;

import com.example.demo.DTO.response.StudentResponse;
import com.example.demo.Mapper.StudentResponseMapper;
import com.example.demo.Model.Student;
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
        studentResponse.setPassword(student.getPassword());
        studentResponse.setUsername(student.getUsername());
        studentResponse.setEmail(student.getEmail());

        return studentResponse;
    }
}
