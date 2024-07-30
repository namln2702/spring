package com.example.demo.Controller;


import com.example.demo.DTO.Request.StudentRequest;
import com.example.demo.Model.Student;
import com.example.demo.Payload.ResponseData;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class StudentController {

    @Autowired
    @Qualifier("studentServiceImpl")
    private StudentService studentService;

    @GetMapping(path="/students")
    public ResponseEntity<?> getStudentController(){
        return studentService.getStudentService();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Custom-Header", "foo");
//
//        return new ResponseEntity<>(
//                "Custom header set", headers, HttpStatus.OK);
    }


    @PostMapping(path="/student")
    public ResponseEntity<?> postStudentController(@Validated @RequestBody StudentRequest studentRequest){

        return studentService.postStudentService(studentRequest);

    }

    @DeleteMapping(path="/student")
    public ResponseEntity<?> deleteStudentController(@RequestParam String username){

        return studentService.deleteStudentService(username);

    }

    @PutMapping(path="/student")
    public ResponseEntity<?> putStudentController(@RequestBody StudentRequest studentRequest){
        return studentService.putStudentService(studentRequest);

    }


}
