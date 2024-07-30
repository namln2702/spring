package com.example.demo.Mapper.MapperImp;

import com.example.demo.DTO.Request.StudentRequest;
import com.example.demo.Mapper.StudentMapper;
import com.example.demo.Model.Student;
import org.springframework.stereotype.Component;

@Component

public class StudentMapperImp implements StudentMapper {

    @Override
    public Student toStudent(StudentRequest studentRequest) {
        Student student = new Student();

        if (studentRequest == null){
            return null;
        }

        student.setName(studentRequest.getName());
        student.setPassword(studentRequest.getPassword());
        student.setUsername(studentRequest.getUsername());
        student.setEmail(studentRequest.getEmail());

        return student;

    }
}
