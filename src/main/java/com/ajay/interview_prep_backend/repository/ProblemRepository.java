package com.ajay.interview_prep_backend.repository;

import com.ajay.interview_prep_backend.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

}
