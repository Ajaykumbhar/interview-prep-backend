package com.ajay.interview_prep_backend.service;

import com.ajay.interview_prep_backend.dto.ProblemDTO;
import com.ajay.interview_prep_backend.dto.ProblemRequest;
import com.ajay.interview_prep_backend.entity.Problem;
import com.ajay.interview_prep_backend.exception.ProblemNotFoundException;
import com.ajay.interview_prep_backend.repository.ProblemRepository;
import org.springframework.stereotype.Service;

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
        Problem problem = problemRepository.findById(id).orElseThrow(()-> new ProblemNotFoundException("Problem not found with id: "+id));
        return toDTO(problem);
    }

    public List<ProblemDTO> getProblemByDifficulty(String difficulty){
        List<Problem> problems = problemRepository.findByDifficulty(difficulty);
        return problems.stream().map(this::toDTO).toList();
    }


    public ProblemDTO createProblem(ProblemRequest request) {
        Problem  problem = new Problem();
        problem.setTitle(request.getTitle());
        problem.setDifficulty(request.getDifficulty());
        Problem savedProblem = problemRepository.save(problem);
        return toDTO(savedProblem);
    }

     public ProblemDTO updateProblem(Long id, ProblemRequest request) {
        Problem problem = problemRepository.findById(id).orElseThrow( ()-> new ProblemNotFoundException("Problem not found with id: "+ id));

        problem.setTitle(request.getTitle());
        problem.setDifficulty(request.getDifficulty());
        Problem updatedProblem = problemRepository.save(problem);
        return toDTO(updatedProblem);
    }

    public void deleteProblem(Long id) {
        Problem problem = problemRepository
                .findById(id)
                .orElseThrow(()-> new ProblemNotFoundException("Problem not found with id: "+id));
        problemRepository.delete(problem);
    }

    private ProblemDTO toDTO(Problem problem) {
       return  new ProblemDTO(problem.getId(), problem.getTitle(), problem.getDifficulty());
    }


}
