package com.example.demo.StudentClass12A1;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping()
public class StudentController {

    private StudentService studentService = new StudentService();


    @GetMapping
    public List<Student> getStudentController(){
        return studentService.getStudentService();
    }

    @PostMapping
    public String postStudentController(@RequestBody Student student){
        String text = new String();
        try{
            studentService.postStudentService(student);
            text = "Successfully";
        }
        catch (Exception e){
            text = "Error";
        }

        return text;
    }

    @DeleteMapping
    public String deleteStudentController(@RequestParam("id") Integer id){

        studentService.deleteStudentService(id);

        return "Successfully";
    }

    @PutMapping
    public String putStudentController(@RequestBody Student student){
        studentService.putStudentService(student);

        return "Succeccfully";
    }


}
