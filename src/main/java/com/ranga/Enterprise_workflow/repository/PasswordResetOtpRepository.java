package com.ranga.Enterprise_workflow.repository;

import com.ranga.Enterprise_workflow.entity.PassWordResetOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetOtpRepository extends JpaRepository<PassWordResetOtp, Long> {

    Optional<PassWordResetOtp> findByEmail(String email);
}