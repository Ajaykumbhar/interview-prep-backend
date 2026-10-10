package com.ajay.interview_prep_backend.service;

import com.ajay.interview_prep_backend.dto.ApiResponseDTO;
import com.ajay.interview_prep_backend.dto.LoginRequest;
import com.ajay.interview_prep_backend.dto.RegisterRequest;
import com.ajay.interview_prep_backend.dto.UserResponse;
import com.ajay.interview_prep_backend.entity.User;
import com.ajay.interview_prep_backend.exception.EmailAlreadyExistsException;
import com.ajay.interview_prep_backend.exception.InvalidCredentialsException;
import com.ajay.interview_prep_backend.repository.UserRepository;
import com.ajay.interview_prep_backend.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public UserService(UserRepository userRepository,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil=jwtUtil;
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
        String token = jwtUtil.generateToken(user.getUserEmail());
        return ApiResponseDTO.builder().status("Success").message("Login  successful!").token(token).build();
    }
    public User findByUserEmail(String email){
        return  userRepository.findByUserEmail(email).orElseThrow(()-> new InvalidCredentialsException("Invalid username or password!"));
    }

    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder().id(user.getId()).userName(user.getUserName()).userEmail(user.getUserEmail()).userRole(user.getUserRole()).build();
    }
}
