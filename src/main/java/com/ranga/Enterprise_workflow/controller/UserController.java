package com.ranga.Enterprise_workflow.controller;

import com.ranga.Enterprise_workflow.dto.ForgotPasswordRequest;
import com.ranga.Enterprise_workflow.dto.LoginRequest;
import com.ranga.Enterprise_workflow.entity.User;
import com.ranga.Enterprise_workflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Authentication API",
        description = "User Registration, Login and Forgot Password APIs"
)
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ===========================
    // Register
    // ===========================
    @Operation(summary = "Register User")
    @ApiResponse(responseCode = "201", description = "User Registered Successfully")
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // ===========================
    // Login
    // ===========================
    @Operation(summary = "Login User")
    @ApiResponse(responseCode = "200", description = "JWT Generated Successfully")
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    // ===========================
    // Forgot Password
    // ===========================
    @Operation(summary = "Send OTP to Email")
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @RequestBody ForgotPasswordRequest request) {

        return ResponseEntity.ok(
                userService.forgotPassword(request.getEmail())
        );
    }

    // ===========================
    // Profile
    // ===========================
    @Operation(summary = "User Profile")
    @GetMapping("/profile")
    public String profile() {
        return "Welcome! JWT Authentication Successful";
    }
}