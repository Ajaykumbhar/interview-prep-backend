package com.ajay.interview_prep_backend.controller;

import com.ajay.interview_prep_backend.dto.ProblemDTO;
import com.ajay.interview_prep_backend.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/problems")
public class ProblemController {

  
    private final ProblemService problemService;
    
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }
    
    @GetMapping
    public List<ProblemDTO> getProblems() {

        return problemService.getAllProblems();
    }

    @GetMapping("/{id}")
    public ProblemDTO getProblemByIId(@PathVariable Long id) {
        return new ProblemDTO(
                id,
                "Sample",
                "easy"
        );
    }
}
