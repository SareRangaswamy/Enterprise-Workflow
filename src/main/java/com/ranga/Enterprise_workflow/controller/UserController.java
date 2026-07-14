package com.ranga.Enterprise_workflow.controller;

import com.ranga.Enterprise_workflow.dto.LoginRequest;
import com.ranga.Enterprise_workflow.entity.User;
import com.ranga.Enterprise_workflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Authentication API",
        description = "User Registration, Login and Profile REST APIs"
)
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register User
    @Operation(summary = "Register New User")
    @ApiResponse(
            responseCode = "201",
            description = "User registered successfully"
    )
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Login User
    @Operation(summary = "User Login")
    @ApiResponse(
            responseCode = "200",
            description = "Login successful and JWT token generated"
    )
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    // User Profile
    @Operation(summary = "Get Logged-in User Profile")
    @ApiResponse(
            responseCode = "200",
            description = "Profile fetched successfully"
    )
    @GetMapping("/profile")
    public String profile() {
        return "Welcome! JWT Authentication Successful";
    }
}