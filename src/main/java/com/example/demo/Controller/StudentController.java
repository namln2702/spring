package com.example.demo.Controller;


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

    @GetMapping(path="/getStudent")
    public ResponseEntity<?> getStudentController(){
        return studentService.getStudentService();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Custom-Header", "foo");
//
//        return new ResponseEntity<>(
//                "Custom header set", headers, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> postStudentController(@Validated @RequestBody Student student){

        return studentService.postStudentService(student);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteStudentController(@RequestParam("id") Integer id){

        return studentService.deleteStudentService(id);

    }

    @PutMapping
    public ResponseEntity<?> putStudentController(@RequestBody Student student){
        return studentService.putStudentService(student);

    }


}
