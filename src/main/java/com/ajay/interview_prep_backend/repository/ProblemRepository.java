package com.ajay.interview_prep_backend.repository;

import com.ajay.interview_prep_backend.dto.ProblemDTO;
import com.ajay.interview_prep_backend.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

    List<Problem> findByDifficulty(String difficulty);
}
