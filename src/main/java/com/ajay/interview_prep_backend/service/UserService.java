package com.ajay.interview_prep_backend.service;

import com.ajay.interview_prep_backend.dto.ApiResponseDTO;
import com.ajay.interview_prep_backend.dto.LoginRequest;
import com.ajay.interview_prep_backend.dto.RegisterRequest;
import com.ajay.interview_prep_backend.dto.UserResponse;
import com.ajay.interview_prep_backend.entity.User;
import com.ajay.interview_prep_backend.exception.EmailAlreadyExistsException;
import com.ajay.interview_prep_backend.exception.InvalidCredentialsException;
import com.ajay.interview_prep_backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserResponse register(RegisterRequest request) {

        if(userRepository.findByUserEmail(request.getUserEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email already exists!");
        }

        User user  = User.builder()
                .userName(request.getUserName())
                .userEmail(request.getUserEmail())
                .userPassword(
                        passwordEncoder.encode(request.getUserPassword())
                )
                .userRole("USER")
                .build();

        User savedUser = userRepository.save(user);

        return mapToUserResponse(savedUser);
    }
    public ApiResponseDTO login(LoginRequest request) {
        User user = userRepository.findByUserEmail(request.getUserEmail()).orElseThrow(()-> new InvalidCredentialsException("Invalid username or password!"));
        boolean matches = passwordEncoder.matches(request.getUserPassword(), user.getUserPassword());
        if(!matches){
            throw new InvalidCredentialsException("Invalid username or password!");
        }
        return ApiResponseDTO.builder().status("Success").message("Login  successful!").build();
    }
    public User findByUserEmail(String email){
        return  userRepository.findByUserEmail(email).orElseThrow(()-> new InvalidCredentialsException("Invalid username or password!"));
    }

    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder().id(user.getId()).userName(user.getUserName()).userEmail(user.getUserEmail()).userRole(user.getUserRole()).build();
    }
}
