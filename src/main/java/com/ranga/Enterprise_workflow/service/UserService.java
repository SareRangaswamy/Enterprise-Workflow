package com.ranga.Enterprise_workflow.service;

import com.ranga.Enterprise_workflow.dto.LoginRequest;
import com.ranga.Enterprise_workflow.entity.User;
import com.ranga.Enterprise_workflow.jwt.JwtService;
import com.ranga.Enterprise_workflow.repository.Userrepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final Userrepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(Userrepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // Register User
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Login User
    public String login(LoginRequest loginRequest) {

        System.out.println("========== LOGIN ==========");
        System.out.println("Email Received : " + loginRequest.getEmail());
        System.out.println("Password Received : " + loginRequest.getPassword());

        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());

        System.out.println("User Present : " + user.isPresent());

        if (user.isEmpty()) {
            return "User not found";
        }

        System.out.println("DB Email : " + user.get().getEmail());

        boolean matches = passwordEncoder.matches(
                loginRequest.getPassword(),
                user.get().getPassword());

        System.out.println("Password Matches : " + matches);

        if (matches) {
            return jwtService.generateToken(user.get().getEmail());
        }

        return "Invalid Password";
    }
}