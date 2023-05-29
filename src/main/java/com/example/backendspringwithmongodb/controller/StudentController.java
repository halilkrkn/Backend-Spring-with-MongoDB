package com.example.backendspringwithmongodb.controller;

import com.example.backendspringwithmongodb.model.Student;
import com.example.backendspringwithmongodb.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // Burada tüm studentlar listeleniyor.
    // http://localhost:8080/api/v1/student
    @GetMapping
    public List<Student> fetchAllStudents(){
        return studentService.getAllStudents();
    }

}
