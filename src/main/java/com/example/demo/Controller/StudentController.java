package com.example.demo.Controller;


import com.example.demo.Model.Student;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class StudentController {


    @Autowired
    private StudentService studentService;

    @GetMapping(path="/getStudent")
    public List<Student> getStudentController(){
        return studentService.getStudentService();
    }


    @PostMapping
    public String postStudentController(@Validated @RequestBody Student student){
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
//
//    @PutMapping
//    public String putStudentController(@RequestBody Student student){
//        studentService.putStudentService(student);
//
//        return "Succeccfully";
//    }


}
