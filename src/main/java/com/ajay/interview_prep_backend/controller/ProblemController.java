package com.ajay.interview_prep_backend.controller;

import com.ajay.interview_prep_backend.dto.ProblemResponse;
import com.ajay.interview_prep_backend.dto.ProblemRequest;
import com.ajay.interview_prep_backend.service.ProblemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

  
    private final ProblemService problemService;
    
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }


    // ********************* Get Mappings *******************************//
    @GetMapping
    public List<ProblemResponse> getProblems(@RequestParam(required = false) String difficulty) {
        if(difficulty !=null && !difficulty.isBlank()){
            return problemService.getProblemByDifficulty(difficulty);
        }

        return problemService.getAllProblems();
    }

    @GetMapping("/{id}")
    public ProblemResponse getProblemByIId(@PathVariable Long id) {
        return problemService.getProblemById(id);
    }






    // ****************************** Post Mappings *****************************//
    @PostMapping
    public ProblemResponse createProblem(@Valid @RequestBody ProblemRequest request) {
        System.out.println("Title = [" + request.getTitle() + "]");
        return problemService.createProblem(request);
    }

    // ****************************** Put Mappings *****************************//

    @PutMapping("/{id}")
    public ProblemResponse updateProblem(@PathVariable Long id, @Valid @RequestBody ProblemRequest request) {
        return problemService.updateProblem(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable Long id) {
        problemService.deleteProblem(id);
        return  ResponseEntity.noContent().build();

    }

}
