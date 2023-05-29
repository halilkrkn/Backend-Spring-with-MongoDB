package com.example.backendspringwithmongodb.service;

import com.example.backendspringwithmongodb.data.StudentRepository;
import com.example.backendspringwithmongodb.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
