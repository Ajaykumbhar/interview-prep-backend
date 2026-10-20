package com.ajay.interview_prep_backend.service;

import com.ajay.interview_prep_backend.dto.ProblemResponse;
import com.ajay.interview_prep_backend.dto.ProblemRequest;
import com.ajay.interview_prep_backend.entity.Problem;
import com.ajay.interview_prep_backend.exception.ProblemNotFoundException;
import com.ajay.interview_prep_backend.repository.ProblemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }


    //***** Get Mapping calla *******?

    public Page<ProblemResponse> searchProblems(
            String search,
            String difficulty,
            Pageable pageable
    ){
        if(search!=null){
            search = search.trim();
            if(search.isEmpty()){
                search=null;
            }
        }

        if(difficulty!=null){
            difficulty=difficulty.trim();
            if(difficulty.isEmpty()){
                difficulty=null;
            }
        }

        Page<Problem> problems = problemRepository.searchProblems(search,difficulty,pageable);
        return problems.map(this::toDTO);
    }


    public Page<ProblemResponse> getAllProblems(Pageable pageable) {
        Page<Problem> problems =  problemRepository.findAll(pageable);
        return problems.map(this::toDTO);
//        return problems.stream().map(problem -> new ProblemResponse(
//                problem.getId(),
//                problem.getTitle(),
//                problem.getDifficulty()
//        )).toList();s
    }

    public ProblemResponse getProblemById(long id) {
        Problem problem = problemRepository.findById(id).orElseThrow(()-> new ProblemNotFoundException("Problem not found with id: "+id));
        return toDTO(problem);
    }

    public Page<ProblemResponse> getProblemByDifficulty(String difficulty, Pageable pageable){
        Page<Problem> problems = problemRepository.findByDifficulty(difficulty,pageable);
        return problems.map(this::toDTO);
    }


    // ****** Post Mappings *******//


    public ProblemResponse createProblem(ProblemRequest request) {
        Problem  problem = new Problem();
        problem.setTitle(request.getTitle());
        problem.setDifficulty(request.getDifficulty());
        Problem savedProblem = problemRepository.save(problem);
        return toDTO(savedProblem);
    }

     public ProblemResponse updateProblem(Long id, ProblemRequest request) {
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

    private ProblemResponse toDTO(Problem problem) {
       return  new ProblemResponse(problem.getId(), problem.getTitle(), problem.getDifficulty());
    }


}
