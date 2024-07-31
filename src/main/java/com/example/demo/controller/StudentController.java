package com.example.demo.controller;



import com.example.demo.DTO.request.StudentRequest;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class StudentController {

    @Autowired
    @Qualifier("studentServiceImpl")
    private StudentService studentService;

    @GetMapping(path="/students")
    public ResponseEntity<?> getStudentController(){
        return studentService.getStudentService();

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
