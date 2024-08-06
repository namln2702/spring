package com.example.demo.mapper.mapperImp;

import com.example.demo.dto.response.StudentResponse;
import com.example.demo.mapper.StudentToStudentResponseMapper;
import com.example.demo.model.Student;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class StudentToStudentResponseMapperImp implements StudentToStudentResponseMapper {
    @Override
    public StudentResponse toStudenReponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();

        if (student == null){
            return null;
        }

        studentResponse.setName(student.getName());
        studentResponse.setUsername(student.getUsername());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setRoles(new HashSet<>(student.getRoles()));
        studentResponse.setAge(student.getAge());

        return studentResponse;
    }
}
