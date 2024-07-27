package com.example.demo.Service.Implement;

import com.example.demo.Model.Student;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Student> getStudentService() {
        return studentRepository.findAll();
    }

    @Override
    public void postStudentService(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudentService(Integer id) {
        studentRepository.deleteById(id);
    }
//
//    @Override
//    public void putStudentService(Student student) {
//        for (Student pos : listStudent){
//            if (pos.getId() == student.getId()){
//                pos.setName(student.getName());
//                pos.setEmail(student.getEmail());
//                break;
//            }
//        }
//    }
//
//    @Override
//    public Student getStudent(Integer id) {
//        for (Student pos : listStudent){
//            if (pos.getId() == id){
//                return pos;
//            }
//        }
//
//        return null;
//    }
}
