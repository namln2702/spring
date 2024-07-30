package com.example.demo.Service.Implement;

import com.example.demo.DTO.Request.StudentRequest;
import com.example.demo.DTO.Response.StudentResponse;
import com.example.demo.Mapper.StudentMapper;
import com.example.demo.Mapper.StudentResponseMapper;
import com.example.demo.Model.Student;
import com.example.demo.Payload.ResponseData;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.*;

@Service

public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentResponseMapper studentResponseMapper;

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public ResponseEntity<?> getStudentService() {
        List<StudentResponse> save = new ArrayList<>();
        ResponseData responseData = new ResponseData();

        for (Student pos : studentRepository.findAll()){
            if (!pos.isDeleted()) save.add(studentResponseMapper.toStudenReponse(pos));
        }

        responseData.setData(save);
        if (save.isEmpty()){
            responseData.setStatus(HttpStatus.BAD_REQUEST.value());
            responseData.setMessage("Table Null");
            return ResponseEntity.badRequest().body(responseData);
        }
        responseData.setStatus(HttpStatus.OK.value());
        responseData.setMessage("Print Student");
        return ResponseEntity.ok().body(responseData);
    }

    @Override
    public ResponseEntity<?> postStudentService(StudentRequest studentRequest) {
        ResponseData responseData = new ResponseData();
        Student student = studentMapper.toStudent(studentRequest);

        String test = student.getUsername();
        Optional<Student> pos = studentRepository.findByUsername(test);
        if (pos.isPresent()){
            responseData.setMessage("User already exists");
            responseData.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(responseData);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        student.setPassword(passwordEncoder.encode(student.getPassword()));

        studentRepository.save(student);

        responseData.setStatus(200);
        responseData.setData(studentResponseMapper.toStudenReponse(student));
        responseData.setMessage("Successfully");
        return ResponseEntity.ok().body(responseData);
    }

    @Override
    public ResponseEntity<?> deleteStudentService(String username) {
        ResponseData responseData = new ResponseData();
        Optional<Student> studentOptional = studentRepository.findByUsername(username);

        if(studentOptional.isPresent() && !studentOptional.get().isDeleted()){
            Student studentDel = studentOptional.get();
            studentDel.setId(studentOptional.get().getId());
            studentDel.setDeleted(true);
            studentRepository.save(studentDel);

            responseData.setStatus(200);
            responseData.setMessage("Delete Successfully");
            responseData.setData(studentResponseMapper.toStudenReponse(studentDel));

            return ResponseEntity.ok().body(responseData);
        }
        responseData.setStatus(HttpStatus.NOT_FOUND.value());
        responseData.setMessage("User not found");
        responseData.setData(null);

        return ResponseEntity.badRequest().body(responseData);

    }

    @Override
    public ResponseEntity<?> putStudentService(StudentRequest studentRequest) {

        Student student = studentMapper.toStudent(studentRequest);

        Optional<Student> putStudent = studentRepository.findByUsername(student.getUsername());
        ResponseData responseData = new ResponseData();

        if(putStudent.isPresent()) {

            student.setId(putStudent.get().getId());

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            student.setPassword(passwordEncoder.encode(student.getPassword()));

            studentRepository.save(student);

            responseData.setStatus(200);
            responseData.setMessage("Update SuccessFully");
            responseData.setData(studentResponseMapper.toStudenReponse(student));

            return ResponseEntity.ok().body(responseData);
        }

        responseData.setStatus(HttpStatus.NOT_FOUND.value());
        responseData.setMessage("User not found");
        responseData.setData(null);

        return ResponseEntity.badRequest().body(responseData);

    }
}
