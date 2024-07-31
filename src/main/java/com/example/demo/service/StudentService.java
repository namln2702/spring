package com.example.demo.service;


import com.example.demo.dto.request.StudentRequest;
import org.springframework.http.ResponseEntity;


public interface StudentService {
    public ResponseEntity<?> getStudentService();

    public ResponseEntity<?> postStudentService(StudentRequest studentRequest);

    public ResponseEntity<?> deleteStudentService(String username);
    public ResponseEntity<?> putStudentService(StudentRequest studentRequest);

}
