package com.ajay.interview_prep_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "problem_progress",
        uniqueConstraints = {
                @UniqueConstraint(
                        name= "uk_progress_user_problem",
                        columnNames = {"user_id","problem_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class ProblemProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private  User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 30)
    private ProgressStatus status;

    @Column(columnDefinition = "TEXT")
    private String notes="";

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt= now;
    }

    @PreUpdate
    protected void onUpdate(){
       updatedAt = LocalDateTime.now();
    }
}
