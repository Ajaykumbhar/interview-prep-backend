package com.ajay.interview_prep_backend.service;

import com.ajay.interview_prep_backend.dto.ProblemDTO;
import com.ajay.interview_prep_backend.entity.Problem;
import com.ajay.interview_prep_backend.repository.ProblemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }


    public List<ProblemDTO> getAllProblems() {
        List<Problem> problems =  problemRepository.findAll();
        return problems.stream().map(this::toDTO).toList();
//        return problems.stream().map(problem -> new ProblemDTO(
//                problem.getId(),
//                problem.getTitle(),
//                problem.getDifficulty()
//        )).toList();
    }

    public ProblemDTO getProblemById(long id) {
        Problem problem = problemRepository.findById(id).orElseThrow();
        return toDTO(problem);
    }

    public List<ProblemDTO> getProblemByDifficulty(String difficulty){
        List<Problem> problems = problemRepository.findByDifficulty(difficulty);
        return problems.stream().map(this::toDTO).toList();
    }

    private ProblemDTO toDTO(Problem problem) {
       return  new ProblemDTO(problem.getId(), problem.getTitle(), problem.getDifficulty());
    }

}
