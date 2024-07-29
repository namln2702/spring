package com.example.demo.Service;


import com.example.demo.Model.Student;
import com.example.demo.Payload.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;



public interface StudentService {
    public ResponseEntity<?> getStudentService();

    public ResponseEntity<?> postStudentService(Student student);

    public ResponseEntity<?> deleteStudentService(Integer id);
    public ResponseEntity<?> putStudentService(Student student);

}
