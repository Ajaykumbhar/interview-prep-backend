package com.ajay.interview_prep_backend.controller;

import com.ajay.interview_prep_backend.dto.ApiResponseDTO;
import com.ajay.interview_prep_backend.dto.LoginRequest;
import com.ajay.interview_prep_backend.dto.RegisterRequest;
import com.ajay.interview_prep_backend.dto.UserResponse;
import com.ajay.interview_prep_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
            UserResponse response= userService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO> login(@Valid @RequestBody LoginRequest request){
        ApiResponseDTO response = userService.login(request);
        return ResponseEntity.ok().body(response);
    }

}
