package com.ajay.interview_prep_backend.controller;


import com.ajay.interview_prep_backend.dto.ProgressRequest;
import com.ajay.interview_prep_backend.dto.ProgressResponse;
import com.ajay.interview_prep_backend.service.ProgressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {
    private final ProgressService progressService;

    public ProgressController(ProgressService progressService){
        this.progressService = progressService;
    }

    @PutMapping("/{problemId}")
    public ResponseEntity<ProgressResponse> updateProgress(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long problemId,
            @Valid @RequestBody ProgressRequest request
            ){
        ProgressResponse response = progressService.updateProgress(
                userDetails.getUsername(),
                problemId,
                request
        );

        return ResponseEntity.ok(response);

    }

    @GetMapping("/me")
    public ResponseEntity<List<ProgressResponse>> getMyProgress(
            @AuthenticationPrincipal UserDetails userDetails
    ){
        List<ProgressResponse> progress = progressService.getMyProgress(userDetails.getUsername());
        return ResponseEntity.ok(progress);

    }

}
