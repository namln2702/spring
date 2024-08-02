package com.example.demo.configration;


import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class ApplicationInitConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(StudentRepository studentRepository){
        return args ->{
            if (studentRepository.findByUsername("admin").isEmpty()){
                String role = "admin";

                Student student = Student.builder()
                        .username("admin")
                        .role("admin")
                        .password(passwordEncoder.encode("admin"))
                        .build();

                studentRepository.save(student);
                log.warn("insert ok");
            }

        };


    }

}
