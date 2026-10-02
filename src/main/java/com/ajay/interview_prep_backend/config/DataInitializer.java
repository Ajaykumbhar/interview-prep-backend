package com.ajay.interview_prep_backend.config;

import com.ajay.interview_prep_backend.dto.ProblemDTO;
import com.ajay.interview_prep_backend.entity.Problem;
import com.ajay.interview_prep_backend.repository.ProblemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadData(ProblemRepository repository) {
        return args -> {
            if(repository.count() == 0) {
                repository.save(
                        new Problem(
                                null,
                                "Two Sum",
                                "Easy"
                        )
                );

                repository.save(
                        new Problem(
                                null,
                                "Three Sum",
                                "Medium"
                        )
                );

                repository.save(
                        new Problem(
                                null,
                                "LRU Cache",
                                "Hard"
                        )
                );
            }
        };
    }
}