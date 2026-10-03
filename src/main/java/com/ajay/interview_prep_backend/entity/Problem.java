package com.ajay.interview_prep_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="problems")
public class Problem {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Column(name="problem_title", unique = true, nullable = false, length = 100)
    private String title;

    @NotBlank(message ="Difficulty cannot be empty")
    @Column(name="problem_difficulty",nullable = false)
    private String difficulty;

    public Problem() {}
    public Problem(Long id, String title, String difficulty) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
    }

}
