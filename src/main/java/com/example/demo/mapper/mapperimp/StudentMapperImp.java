package com.example.demo.mapper.mapperimp;

import com.example.demo.dto.request.StudentRequest;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Student;
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
