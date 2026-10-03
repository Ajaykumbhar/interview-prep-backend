package com.ajay.interview_prep_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProblemRequest {

    @NotBlank(message = "Title Cannot be empty")
    @Size(min =3,max=100, message = "Title should be between 3 and 100 characters.")
    private String title;

    @NotBlank(message = "Difficulty Cannot be empty")
    private String difficulty;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
