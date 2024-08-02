package com.example.demo.controller;



import com.example.demo.dto.request.StudentRequest;
import com.example.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping()
public class StudentController {

    @Autowired
    @Qualifier("studentServiceImpl")
    private StudentService studentService;




    // block before called
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    @GetMapping(path="/students")
    public ResponseEntity<?> getStudentsController(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("{username : {}", authentication.getAuthorities());

        return studentService.getStudentsService();

    }


    //Block after called
    @PostAuthorize("returnObject.getBody().data.username == authentication.name")
    @GetMapping(path = "/student")
    public ResponseEntity<?> getStudentController(@RequestParam String username){
        return studentService.getStudentService(username);
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
