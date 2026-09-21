package com.ajay.interview_prep_backend.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getGreeting(){
        return "Hello Service Layer here!";
    }
}
