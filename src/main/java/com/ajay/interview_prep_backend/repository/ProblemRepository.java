package com.ajay.interview_prep_backend.repository;

import com.ajay.interview_prep_backend.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProblemRepository extends JpaRepository<Problem, Long> {

    Page<Problem> findByDifficulty(String difficulty, Pageable pageable);

    @Query("""
        SELECT p FROM Problem p
        WHERE (:search IS NULL
                OR LOWER(p.title) LIKE LOWER(CONCAT('%',:search,'%')))
        AND   (:difficulty IS NULL
                OR p.difficulty = :difficulty)
    """)
    Page<Problem> searchProblems(@Param("search") String search,
                                 @Param("difficulty") String difficulty,
                                 Pageable pageable);

}
