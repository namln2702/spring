package com.example.demo.StudentClass12A1;


import java.util.ArrayList;
import java.util.List;


public class StudentService {

    private List<Student> listStudent = new ArrayList<>();

    public StudentService(){

    }
    public List<Student> getStudentService(){
        return listStudent;
    }

    public void postStudentService(Student student){
        listStudent.add(student);
    }

    public void deleteStudentService(Integer id){
        for (Student pos : listStudent){
            if (pos.getId() == id){
                listStudent.remove(pos);
                break;
            }
        }
    }

    public void putStudentService(Student student){
        for (Student pos : listStudent){
            if (pos.getId() == student.getId()){
                pos.setName(student.getName());
                pos.setEmail(student.getEmail());
                break;
            }
        }
    }
}
