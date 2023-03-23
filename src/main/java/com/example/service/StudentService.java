package com.example.service;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student(createStudentRequest);
        student = studentRepository.save(student);
        return student;
    }

    public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
        Student student = studentRepository.findById(updateStudentRequest.getId()).get();

        if (updateStudentRequest.getFirstName() != null &&
                !updateStudentRequest.getFirstName().isEmpty()) {
            student.setFirstName(updateStudentRequest.getFirstName());
        }
        student = studentRepository.save(student);
        return student;
    }

    public String deleteStudent(long id) {
        studentRepository.deleteById(id);
        return "Student Has been deleted Successfully";
    }

    public List<Student> getByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    public Student getByFirstNametAndLastName(String firstName, String lastName) {
       //return studentRepository.findByFirstNameAndLastName(firstName, lastName);
        return studentRepository.getByFirstNameAndLastName(firstName, lastName);

    }

    public List<Student> getByFirstNametOrLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameOrLastName(firstName, lastName);
    }

    public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest) {
        return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
    }

    public List<Student> getAllStudentWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return studentRepository.findAll(pageable).getContent();
    }

    public List<Student> getAllStudentWithSorting() {
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName", "lastName");
        return studentRepository.findAll(sort);
    }

    public Integer updateStudentWithJpql(Long id, String firstName) {
        return studentRepository.updateFirstName(id, firstName);
    }
    public Integer deleteStudent (String firstName){
    return studentRepository.deleteByFirstName(firstName);
    }
}
