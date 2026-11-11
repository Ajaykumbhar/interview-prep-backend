package com.ajay.interview_prep_backend.service;


import com.ajay.interview_prep_backend.dto.ProgressRequest;
import com.ajay.interview_prep_backend.dto.ProgressResponse;
import com.ajay.interview_prep_backend.entity.*;
import com.ajay.interview_prep_backend.repository.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class ProgressServiceTest {


    @Mock
    private ProblemProgressRepository progressRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProblemRepository problemRepository;

    @InjectMocks
    private ProgressService progressService;

    @Test
    void shouldCreateProgressWhenUserAndProblemExist(){

        // Arrange: Prepare test data

        User user = User.builder()
                .id(1L)
                .userName("Ajay")
                .userEmail("ajay.test@example.com")
                .userPassword("encoded-password")
                .userRole("USER")
                .build();

        Problem problem = new Problem(
                1L,
                "Two Sum",
                "Easy"
        );

        ProgressRequest request = new ProgressRequest(
                ProgressStatus.SOLVED,
                "Solved using HashMap"
        );

        //Mock User lookup
        when(userRepository.findByUserEmail("ajay.test@example.com"))
                .thenReturn(Optional.of(user));

        // Mock Problem lookup
        when(problemRepository.findById(1L))
                .thenReturn(Optional.of(problem));

        // No existing progress
        when(progressRepository.findProgress(1L,1L))
                .thenReturn(Optional.empty());

        //Mock repository save
        when(progressRepository.save(any(ProblemProgress.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        // Act: Execute business logic

        ProgressResponse response = progressService.updateProgress(
                "ajay.test@example.com",
                1L,
                request
        );

        // Assert: Verify expected result

        assertNotNull(response);
        assertEquals(1L, response.problemId());
        assertEquals("Two Sum", response.problemTitle());
        assertEquals("SOLVED", response.status());
        assertEquals("Solved using HashMap", response.notes());

        verify(progressRepository,times(1))
                .save(any(ProblemProgress.class));
    }

    @Test
    void shouldUpdateExistingProgress() {

        // Arrange

        // 1. Create an existing User
        // 2. Create an existing Problem
        // 3. Create an existing ProblemProgress
        // 4. Create a request with status NEEDS_REVISION
        // 5. Mock all three repositories
        // 6. Return Optional.of(existingProgress)
        //    from progressRepository.findProgress()

        // Act

        // Call progressService.updateProgress()

        // Assert

        // Verify the returned status is NEEDS_REVISION
        // Verify updated notes
        // Verify save() was called exactly once
    }

}
