package com.example.demo.Service;


import com.example.demo.Model.Student;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;



public interface StudentService {
    public List<Student> getStudentService();

    public void postStudentService(Student student);

    public void deleteStudentService(Integer id);
//    public void putStudentService(Student student);
//    public Student getStudent(Integer id);

//    public List<Student> getStudentService(){
//        return listStudent;
//    }
//
//    public void postStudentService(Student student){
//        listStudent.add(student);
//    }
//
//    public void deleteStudentService(Integer id){
//        for (Student pos : listStudent){
//            if (pos.getId() == id){
//                listStudent.remove(pos);
//                break;
//            }
//        }
//    }
//
//    public void putStudentService(Student student){
//        for (Student pos : listStudent){
//            if (pos.getId() == student.getId()){
//                pos.setName(student.getName());
//                pos.setEmail(student.getEmail());
//                break;
//            }
//        }
//    }
//
//    public Student getStudent(Integer id){
//        for (Student pos : listStudent){
//            if (pos.getId() == id){
//                return pos;
//            }
//        }
//
//        return null;
//    }
}
