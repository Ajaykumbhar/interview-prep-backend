package com.ajay.interview_prep_backend.dto;

import com.ajay.interview_prep_backend.entity.ProgressStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProgressRequest(

    @NotNull(message = "Status is Required")
    ProgressStatus status,

    @Size(max = 5000, message = "Notes cannot exceed 5000 characters")
    String notes
    ){

}

