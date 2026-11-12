
package com.ajay.interview_prep_backend.repository;

import com.ajay.interview_prep_backend.entity.ProblemProgress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface ProblemProgressRepository
        extends JpaRepository<ProblemProgress, Long> {

    @Query("""
        SELECT pp
        FROM ProblemProgress pp
        WHERE pp.user.id = :userId
          AND pp.problem.id = :problemId
        """)
    Optional<ProblemProgress> findProgress(
            @Param("userId") Long userId,
            @Param("problemId") Long problemId
    );

    Page<ProblemProgress> findAllByUser_IdOrderByUpdatedAtDesc(Long userId, Pageable pageable);
}
