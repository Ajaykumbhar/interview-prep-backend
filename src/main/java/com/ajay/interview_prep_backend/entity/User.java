package com.ajay.interview_prep_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name",length = 100,nullable = false)
    private String userName;

    @Column(name="user_email", unique = true, nullable = false)
    private String userEmail;

    @Column(name="user_password",nullable = false)
    private String userPassword;

    @Column(name="user_role",length = 20,nullable = false)
    private String userRole;

}
