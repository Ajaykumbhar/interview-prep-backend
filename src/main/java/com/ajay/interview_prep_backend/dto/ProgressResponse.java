package com.ajay.interview_prep_backend.dto;

import com.ajay.interview_prep_backend.entity.ProgressStatus;

import java.time.LocalDateTime;

public record ProgressResponse(
        Long problemId,
        String problemTitle,
        String status,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}
