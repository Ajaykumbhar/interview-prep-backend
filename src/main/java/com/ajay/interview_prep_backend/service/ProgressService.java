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
    public List<ProgressResponse> getMyProgress(String userEmail){
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        return progressRepository
                .findAllByUser_IdOrderByUpdatedAtDesc(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
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
