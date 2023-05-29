package com.example.backendspringwithmongodb;

import com.example.backendspringwithmongodb.data.StudentRepository;
import com.example.backendspringwithmongodb.model.Address;
import com.example.backendspringwithmongodb.model.Gender;
import com.example.backendspringwithmongodb.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class BackendSpringWithMongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendSpringWithMongoDbApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
        return args -> {
            Address address = new Address(
                    "England",
                    "E1",
                    "London"
            );

            String email = "Roj@gmail.com";
            Student student = new Student(
                    "Roj",
                    "Khan",
                    email,
                    Gender.MALE,
                    address,
                    List.of("Computer Science", "Math"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
            );

//            usingMongoTeamplateAndQuery(repository, mongoTemplate, email, student);

            repository.findStudentByEmail(email)
                    .ifPresentOrElse(s -> {
                        System.out.println("Student " + s + " already exists");
                    }, () -> {
                        System.out.println("Inserting student " + student);
                        repository.insert(student);
                    });
        };
    }

    // Burada email ile student aranıyor.
    // Eğer student yoksa student insert ediliyor.
    // Eğer student varsa student zaten var diye bir mesaj yazdırılıyor.
    private static void usingMongoTeamplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
        // Burada ne yapılıyor?
        // Burada aynı mailden bir student olup olmadığı kontrol ediliyor.
        Query query = new Query();

        // Burada addCriteria ile email ile arama yapılıyor.1
        query.addCriteria(Criteria.where("email").is(email));

        // Burada aynı mailden bir student olup olmadığı kontrol ediliyor.
        List<Student> students = mongoTemplate.find(query, Student.class);
        // Burada ne yapılıyor?
        // 1. önce email ile student aranıyor
        if (students.size() > 1) {
            throw new IllegalStateException("found may students with email " + email);
        }

        // 2. eğer student yoksa student insert ediliyor
        // 3. eğer student varsa student zaten var diye bir mesaj yazdırılıyor
        if (students.isEmpty()) {
            System.out.println("Inserting student " + student);
            repository.insert(student);
        } else {
            System.out.println(student + " already exists");
        }
    }
}
