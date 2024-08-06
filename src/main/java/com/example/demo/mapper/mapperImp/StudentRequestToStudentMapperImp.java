package com.example.demo.mapper.mapperImp;

import com.example.demo.dto.request.StudentRequest;
import com.example.demo.mapper.StudentRequestToStudentMapper;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component

public class StudentRequestToStudentMapperImp implements StudentRequestToStudentMapper {



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
        student.setAge(studentRequest.getAge());

        return student;

    }
}
