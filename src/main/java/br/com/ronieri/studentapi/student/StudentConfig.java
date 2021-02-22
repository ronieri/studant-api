package br.com.ronieri.studentapi.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student ronieri = new Student(
                    "Ronieri",
                    "ronieri@email.com",
                    LocalDate.of(1990, Month.JANUARY, 1)
            );

            Student renata = new Student(
                    "Renata",
                    "renata@email.com",
                    LocalDate.of(1995, Month.JANUARY, 1)
            );

            studentRepository.saveAll(List.of(ronieri, renata));
        };
    }
}
