package com.example.demo.service.implement;

import com.example.demo.dto.request.StudentRequest;
import com.example.demo.dto.response.StudentResponse;
import com.example.demo.mapper.StudentRequestToStudentMapper;
import com.example.demo.mapper.StudentToStudentResponseMapper;
import com.example.demo.model.Student;
import com.example.demo.payload.ResponseData;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service

public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StudentToStudentResponseMapper studentToStudentResponseMapper;

    @Autowired
    private StudentRequestToStudentMapper studentMapper;

    @Autowired
    PasswordEncoder passwordEncoder;




    @Override
    public ResponseEntity<?> getStudentsService() {
        List<StudentResponse> save = new ArrayList<>();
        ResponseData responseData = new ResponseData();

        for (Student pos : studentRepository.findAll()){
            if (!pos.isDeleted()) save.add(studentToStudentResponseMapper.toStudenReponse(pos));
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
    public ResponseEntity<?> getStudentService(String username){
        Optional<Student> student = studentRepository.findByUsername(username);
        ResponseData responseData = new ResponseData();

        if (student.isPresent() && !student.get().isDeleted()){
            responseData.setStatus(HttpStatus.OK.value());
            responseData.setMessage("Information for username: " + username);
            responseData.setData(studentToStudentResponseMapper.toStudenReponse(student.get()));
            return ResponseEntity.ok().body(responseData);
        }

        responseData.setStatus(HttpStatus.BAD_REQUEST.value());
        responseData.setMessage("User not found");
        return ResponseEntity.badRequest().body(responseData);
    }

    @Transactional
    @Override
    public ResponseEntity<?> postStudentService(StudentRequest studentRequest) {
        ResponseData responseData = new ResponseData();
        Student student = studentMapper.toStudent(studentRequest);
        student.setRoles(new HashSet<>(roleRepository.findAllById(studentRequest.getRoles())));

        String test = student.getUsername();
        Optional<Student> pos = studentRepository.findByUsername(test);
        if (pos.isPresent()){
            responseData.setMessage("User already exists");
            responseData.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(responseData);
        }

        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setRoles(new HashSet<>(roleRepository.findAllById(studentRequest.getRoles())));

        studentRepository.save(student);


        responseData.setStatus(200);
        responseData.setData(studentToStudentResponseMapper.toStudenReponse(student));
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
            responseData.setData(studentToStudentResponseMapper.toStudenReponse(studentDel));

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

            student.setRoles(new HashSet<>(roleRepository.findAllById(studentRequest.getRoles())));
            studentRepository.save(student);

            responseData.setStatus(200);
            responseData.setMessage("Update SuccessFully");
            responseData.setData(studentToStudentResponseMapper.toStudenReponse(student));

            return ResponseEntity.ok().body(responseData);
        }

        responseData.setStatus(HttpStatus.NOT_FOUND.value());
        responseData.setMessage("User not found");
        responseData.setData(null);

        return ResponseEntity.badRequest().body(responseData);

    }
}
