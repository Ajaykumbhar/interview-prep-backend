package com.ajay.interview_prep_backend.controller;

import com.ajay.interview_prep_backend.dto.ApiResponseDTO;
import com.ajay.interview_prep_backend.service.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    private final GreetingService greetingService;

    public HealthController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
    @GetMapping("/health/{name}")
    public ApiResponseDTO hello(@PathVariable String name) {
        return ApiResponseDTO.builder().status("Success").message(greetingService.getGreeting() + " " +name ).build();
    }

    @GetMapping("/health")
    public ApiResponseDTO health() {
        return ApiResponseDTO.builder().status("Success").message(greetingService.getGreeting()).build();
    }

    @GetMapping("/greet")
    public ApiResponseDTO greet(@RequestParam String name) {
        return ApiResponseDTO.builder().status("Success").message(greetingService.getGreeting() + " ::: " +name ).build();
    }
}
