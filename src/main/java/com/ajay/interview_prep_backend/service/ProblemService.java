package com.ajay.interview_prep_backend.service;

import com.ajay.interview_prep_backend.dto.ProblemDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ProblemService {

    public List<ProblemDTO> getProblems() {
        List<ProblemDTO> problems = List.of(
                new ProblemDTO(
                        1L,
                        "Two Sum",
                        "Easy"
                        ),
                new ProblemDTO(
                        2L,
                        "Three Sum",
                        "Medium"
                ),
                new ProblemDTO(
                        3L,
                        "LRU Cache",
                        "Hard"
                )
        );
        return problems;
    }
}
