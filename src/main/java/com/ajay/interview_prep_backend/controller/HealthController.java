package com.ajay.interview_prep_backend.controller;

import com.ajay.interview_prep_backend.dto.ApiResponse;
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
    public ApiResponse hello(@PathVariable String name) {
        return new ApiResponse("Success", greetingService.getGreeting() + " " +name );
    }

    @GetMapping("/health")
    public ApiResponse health() {
        return new ApiResponse("Success", greetingService.getGreeting());
    }

    @GetMapping("/greet")
    public ApiResponse greet(@RequestParam String name) {
        return new ApiResponse("Success", greetingService.getGreeting() + " ::: " +name );
    }
}
