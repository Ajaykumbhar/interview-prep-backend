package com.ajay.interview_prep_backend.repository;

import com.ajay.interview_prep_backend.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProblemRepository extends JpaRepository<Problem, Long> {

    Page<Problem> findByDifficulty(String difficulty, Pageable pageable);
}
