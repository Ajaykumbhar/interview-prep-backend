package com.ajay.interview_prep_backend.dto;

public class ProblemDTO {
    private Long id;
    private String title;
    private String difficulty;

    public  ProblemDTO() {
    }
    public ProblemDTO(Long id, String title, String difficulty) {
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
}
