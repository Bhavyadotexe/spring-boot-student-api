package com.example.controller;

import com.example.response.StudentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Student/")
public class StudentController {

    @Value("${app.name: Default Demo App}")
    private String appName;

    @GetMapping("/get")
    private StudentResponse getStudent() {
        StudentResponse studentResponse = new StudentResponse
                (7, "Bhawana", "Kandalakr");
        return studentResponse;
    }

}
