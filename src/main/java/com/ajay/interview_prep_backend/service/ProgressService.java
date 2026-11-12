package com.ajay.interview_prep_backend.service;

import com.ajay.interview_prep_backend.dto.ProgressRequest;
import com.ajay.interview_prep_backend.dto.ProgressResponse;
import com.ajay.interview_prep_backend.entity.Problem;
import com.ajay.interview_prep_backend.entity.ProblemProgress;
import com.ajay.interview_prep_backend.entity.User;
import com.ajay.interview_prep_backend.exception.ProblemNotFoundException;
import com.ajay.interview_prep_backend.exception.UserNotFoundException;
import com.ajay.interview_prep_backend.repository.ProblemProgressRepository;
import com.ajay.interview_prep_backend.repository.ProblemRepository;
import com.ajay.interview_prep_backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProgressService {

    private final ProblemProgressRepository progressRepository;
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;

    public ProgressService(ProblemProgressRepository progressRepository,
                           ProblemRepository problemRepository,
                           UserRepository userRepository){
        this.progressRepository = progressRepository;
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ProgressResponse updateProgress (
            String userEmail,
            Long problemId,
            ProgressRequest request
    ){
        //Identify authenticated user
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(()-> new UserNotFoundException("User not Found"));

        //Find requested problem
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(()->new ProblemNotFoundException("Problem not found with id: "+problemId));

        //find or create Progress
        ProblemProgress progress = progressRepository.findProgress(user.getId(),problemId)
                .orElseGet(ProblemProgress::new);

        progress.setUser(user);
        progress.setProblem(problem);
        progress.setStatus(request.status());
        progress.setNotes(
                request.notes()== null
                        ? ""
                        : request.notes().trim()
        );

        //persist the entity
        ProblemProgress savedProgress = progressRepository.save(progress);

        return mapToResponse(savedProgress);

    }

    @Transactional(readOnly = true)
    public Page<ProgressResponse> getMyProgress(String userEmail, Pageable pageable){
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        return progressRepository
                .findAllByUser_IdOrderByUpdatedAtDesc(
                        user.getId(),
                        pageable
                )
                .map(this::mapToResponse);
    }

    @Transactional(readOnly = true)
    public ProgressResponse getProgress(String userEmail, Long problemId){
         //Step1 : User identification
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(()->
                        new UserNotFoundException("User not found"));
        //Step2 : Problem Exist?
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(()-> new ProblemNotFoundException("Problem not found with Id: "+problemId));

        // Search Progress
        return progressRepository.findProgress(user.getId(),problemId)
                .map(this::mapToResponse)
                .orElseGet(() -> new ProgressResponse(
                        problem.getId(),
                        problem.getTitle(),
                        "NOT_STARTED",
                        "",
                        null,
                        null
                ));

    }



    private  ProgressResponse mapToResponse(ProblemProgress progress){
        return new ProgressResponse(
                progress.getProblem().getId(),
                progress.getProblem().getTitle(),
                progress.getStatus().name(),
                progress.getNotes(),
                progress.getCreatedAt(),
                progress.getUpdatedAt()
        );
    }

}
