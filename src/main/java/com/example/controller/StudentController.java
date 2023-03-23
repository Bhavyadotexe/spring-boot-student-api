package com.example.controller;

import com.example.entity.Student;
import com.example.request.CreateStudentRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("create")
    public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest studentRequest) {
        Student student = studentService.createStudent(studentRequest);
        return new StudentResponse(student);
    }

    @PutMapping("update")
    public StudentResponse UpdateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest) {
        Student student = studentService.updateStudent(updateStudentRequest);
        return new StudentResponse(student);
    }

    /* @DeleteMapping("delete")
         public String deleteStudent(@RequestParam("id") long id){
         return studentService.deleteStudent(id);
     }*/
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("getByFirstName/{firstName}")
    public List<StudentResponse> getByFirstName(@PathVariable String firstName) {
        List<Student> studentList = studentService.getByFirstName(firstName);

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;
    }

    @GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
    public StudentResponse getByFirstNameAndLastName(@PathVariable String firstName,
                                                           @PathVariable String lastName) {
        return new StudentResponse(studentService.getByFirstNametAndLastName(firstName, lastName));
    }

    @GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
    public List<StudentResponse> getByFirstNameOrLastName(@PathVariable String firstName,
                                                          @PathVariable String lastName) {
        List<Student> studentList = studentService.getByFirstNametOrLastName(firstName, lastName);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;
    }

    @GetMapping("getByFirstNameIn")
    public List<StudentResponse> getByFirstNameIn(
            @RequestBody InQueryRequest inQueryRequest) {
        List<Student> studentList = studentService.getByFirstNameIn(inQueryRequest);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;
    }

    @GetMapping("getAllWithPagination")
    public List<StudentResponse> getAllStudentWithPagination(@RequestParam int pageNo,
                                                             @RequestParam int pageSize) {
        List<Student> studentList = studentService
                .getAllStudentWithPagination(pageNo, pageSize);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;
    }

    public List<StudentResponse> getAllStudentWithSorting() {
        List<Student> studentList = studentService.getAllStudentWithSorting();
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;
    }
    @PutMapping("updateFirstName/{id}/{firstName}")
    public String updateStudentWithJpql (@PathVariable Long id, @PathVariable String firstName){
        return studentService.updateStudentWithJpql(id, firstName)+ "Student(s) updated";
    }

    @DeleteMapping("deleteByFirstName/{firstName}")
    public String deleteStudent (@PathVariable String firstName){
        return studentService.deleteStudent(firstName) + "Student(s) deleted";
    }

}



