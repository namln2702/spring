package com.example.demo.service;


import com.example.demo.dto.request.StudentRequest;
import org.springframework.http.ResponseEntity;


public interface StudentService {
    public ResponseEntity<?> getStudentsService();
    public ResponseEntity<?> getStudentService(String username);


    public ResponseEntity<?> postStudentService(StudentRequest studentRequest);

    public ResponseEntity<?> deleteStudentService(String username);
    public ResponseEntity<?> putStudentService(StudentRequest studentRequest);

}
