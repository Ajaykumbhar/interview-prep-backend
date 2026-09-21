package com.ajay.interview_prep_backend.controller;

import com.ajay.interview_prep_backend.service.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    private final GreetingService greetingService;

    public HealthController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
    @GetMapping("/health")
    public String health(){
        return greetingService.getGreeting();
    }
}
