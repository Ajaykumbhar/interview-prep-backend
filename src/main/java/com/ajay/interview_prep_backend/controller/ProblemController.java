package com.ajay.interview_prep_backend.controller;

import com.ajay.interview_prep_backend.dto.ProblemDTO;
import com.ajay.interview_prep_backend.dto.ProblemRequest;
import com.ajay.interview_prep_backend.service.ProblemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problems")
public class ProblemController {

  
    private final ProblemService problemService;
    
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }


    // ********************* Get Mappings *******************************//
    @GetMapping
    public List<ProblemDTO> getProblems() {

        return problemService.getAllProblems();
    }

    @GetMapping("/{id}")
    public ProblemDTO getProblemByIId(@PathVariable Long id) {
        return problemService.getProblemById(id);
    }

    @GetMapping("/difficulty/{difficulty}")
    public List<ProblemDTO> getProblemByDifficulty(@PathVariable String difficulty) {
        System.out.println("Difficulty: " + difficulty);
        return problemService.getProblemByDifficulty(difficulty);
    }


    // ****************************** Post Mappings *****************************//
    @PostMapping
    public ProblemDTO createProblem(@Valid @RequestBody ProblemRequest request) {
        System.out.println("Title = [" + request.getTitle() + "]");
        return problemService.createProblem(request);
    }

    // ****************************** Put Mappings *****************************//

    @PutMapping("/{id}")
    public ProblemDTO updateProblem(@PathVariable Long id, @Valid @RequestBody ProblemRequest request) {
        return problemService.updateProblem(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable Long id) {
        problemService.deleteProblem(id);
        return  ResponseEntity.noContent().build();

    }

}
