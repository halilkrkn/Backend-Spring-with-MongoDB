package com.example.backendspringwithmongodb.data;

import com.example.backendspringwithmongodb.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface StudentRepository
        extends MongoRepository<Student, String> {

     Optional<Student> findStudentByEmail(String email);
}
