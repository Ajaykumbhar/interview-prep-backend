package com.ajay.interview_prep_backend.dto;

public class ProblemResponse {
    private Long id;
    private String title;
    private String difficulty;

    public ProblemResponse() {
    }
    public ProblemResponse(Long id, String title, String difficulty) {
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
