package com.ajay.interview_prep_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message ="Difficulty cannot be empty")
    private String difficulty;

    public Problem() {}
    public Problem(Long id, String title, String difficulty) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
