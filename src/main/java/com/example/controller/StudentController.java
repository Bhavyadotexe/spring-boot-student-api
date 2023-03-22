package com.example.controller;

import com.example.entity.Student;
import com.example.response.StudentResponse;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/Student/")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/getAll")
    public List<StudentResponse> getAllStudents() {
        List<Student> studentList = studentService.getAllStudents();
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;
    }
}
