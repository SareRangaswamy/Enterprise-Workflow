package com.ranga.Enterprise_workflow.service;

import com.ranga.Enterprise_workflow.dto.LoginRequest;
import com.ranga.Enterprise_workflow.entity.PassWordResetOtp;
import com.ranga.Enterprise_workflow.entity.Role;
import com.ranga.Enterprise_workflow.entity.User;
import com.ranga.Enterprise_workflow.jwt.JwtService;
import com.ranga.Enterprise_workflow.repository.PasswordResetOtpRepository;
import com.ranga.Enterprise_workflow.repository.Rolerepository;
import com.ranga.Enterprise_workflow.repository.Userrepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final Userrepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final Rolerepository roleRepository;
    private final PasswordResetOtpRepository otpRepository;
    private final EmailService emailService;

    public UserService(Userrepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       Rolerepository roleRepository,
                       PasswordResetOtpRepository otpRepository,
                       EmailService emailService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.roleRepository = roleRepository;
        this.otpRepository = otpRepository;
        this.emailService = emailService;
    }

    private String generateOtp() {
        return String.valueOf((int) (100000 + Math.random() * 900000));
    }

    // Register User
    public User saveUser(User user) {

        Role role = roleRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));

        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // Login User
    public String login(LoginRequest loginRequest) {

        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());

        if (user.isEmpty()) {
            return "User not found";
        }

        boolean matches = passwordEncoder.matches(
                loginRequest.getPassword(),
                user.get().getPassword());

        if (matches) {
            return jwtService.generateToken(user.get().getEmail());
        }

        return "Invalid Password";
    }

    // Forgot Password
    public String forgotPassword(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return "User not found";
        }

        String otp = generateOtp();

        PassWordResetOtp resetOtp = otpRepository
                .findByEmail(email)
                .orElse(new PassWordResetOtp());

        resetOtp.setEmail(email);
        resetOtp.setOtp(otp);
        resetOtp.setExpiryTime(LocalDateTime.now().plusMinutes(5));

        otpRepository.save(resetOtp);

        emailService.sendOtpEmail(email, otp);

        return "OTP sent successfully";
    }
}