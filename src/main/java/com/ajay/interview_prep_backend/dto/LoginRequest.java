package com.ajay.interview_prep_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @Email(message = "Email Should be valid.")
    @NotBlank(message = "Email is required.")
    private String userEmail;
    @NotBlank(message = "Password is required.")
    private String userPassword;

}
