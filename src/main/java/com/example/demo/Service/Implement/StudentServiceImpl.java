package com.example.demo.Service.Implement;

import com.example.demo.Model.Student;
import com.example.demo.Payload.ResponseData;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.*;

@Service

public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public ResponseEntity<?> getStudentService() {
        List<Student> save = new ArrayList<>();
        ResponseData responseData = new ResponseData();
        for (Student pos : studentRepository.findAll()){
            if (!pos.isDeleted()) save.add(pos);
        }

        Collections.sort(save, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getId() - o2.getId();
            }
        });


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
    public ResponseEntity<?> postStudentService(Student student) {
        String message = new String("Successfully");
        ResponseData responseData = new ResponseData();
        String test = student.getName();
        Optional<Student> pos = studentRepository.findByName(test);
        if (pos.isPresent()){
            responseData.setMessage("user already exists");
            responseData.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(responseData);
        }
        studentRepository.save(student);
        responseData.setStatus(200);
        responseData.setData(student);
        responseData.setMessage("Successfully");
        return ResponseEntity.ok().body(responseData);
    }

    @Override
    public ResponseEntity<?> deleteStudentService(Integer id) {
        ResponseData responseData = new ResponseData();
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent() && !studentOptional.get().isDeleted()){
            Student studentDel = studentOptional.get();
            studentDel.setDeleted(true);
            studentRepository.save(studentDel);

            responseData.setStatus(200);
            responseData.setMessage("Delete Successfully");
            responseData.setData(HttpStatus.OK.value());

            return ResponseEntity.ok().body(responseData);
        }
        responseData.setStatus(HttpStatus.BAD_REQUEST.value());
        responseData.setMessage("Delete Error");
        responseData.setData(null);

        return ResponseEntity.badRequest().body(responseData);

    }

    @Override
    public ResponseEntity<?> putStudentService(Student student) {

        Optional<Student> putStudent = studentRepository.findById(student.getId());
        ResponseData responseData = new ResponseData();

        if(putStudent.isPresent() ) {

            Student pullStudent = studentRepository.save(student);

            responseData.setStatus(200);
            responseData.setMessage("Update SuccessFully");
            responseData.setData(pullStudent);
            return ResponseEntity.ok().body(responseData);
        }

        responseData.setStatus(HttpStatus.BAD_REQUEST.value());
        responseData.setMessage("Update SuccessFully");
        responseData.setData(null);

        return ResponseEntity.badRequest().body(responseData);

    }
}
