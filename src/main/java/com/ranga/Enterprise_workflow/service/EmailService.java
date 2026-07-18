package com.ranga.Enterprise_workflow.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // =====================================================
    // Generic Email
    // =====================================================

    public void sendEmail(String to,
                          String subject,
                          String body) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    // =====================================================
    // Generic Notification
    // =====================================================

    public void sendEmployeeNotification(String to,
                                         String name,
                                         String subject,
                                         String message) {

        sendEmail(
                to,
                subject,
                "Hello " + name + ",\n\n"
                        + message
                        + "\n\nRegards,\nEnterprise Workflow Team"
        );
    }

    // =====================================================
    // USER REGISTERED
    // =====================================================

    public void sendUserRegistrationEmail(String to,
                                          String name) {

        sendEmployeeNotification(
                to,
                name,
                "Registration Successful",
                "Welcome to Enterprise Workflow.\n\n"
                        + "Your account has been registered successfully."
        );
    }

    // =====================================================
    // LOGIN ALERT
    // =====================================================

    public void sendLoginAlertEmail(String to,
                                    String name) {

        sendEmployeeNotification(
                to,
                name,
                "New Login Detected",
                "Your account has been logged in successfully.\n\n"
                        + "If this wasn't you, please reset your password immediately."
        );
    }

    // =====================================================
    // PASSWORD RESET OTP
    // =====================================================

    public void sendOtpEmail(String to,
                             String otp) {

        sendEmail(
                to,
                "Password Reset OTP",
                "Hello,\n\n"
                        + "Your OTP for password reset is : "
                        + otp
                        + "\n\nThis OTP is valid for 10 minutes."
                        + "\n\nDo NOT share it with anyone."
                        + "\n\nEnterprise Workflow Team"
        );
    }

    // =====================================================
    // PASSWORD CHANGED
    // =====================================================

    public void sendPasswordChangedEmail(String to,
                                         String name) {

        sendEmployeeNotification(
                to,
                name,
                "Password Changed Successfully",
                "Your password has been changed successfully."
        );
    }

    // =====================================================
    // WELCOME EMPLOYEE
    // =====================================================

    public void sendWelcomeEmail(String to,
                                 String employeeName) {

        sendEmployeeNotification(
                to,
                employeeName,
                "Welcome to Enterprise Workflow",
                "Your employee account has been created successfully."
        );
    }

    // =====================================================
    // PROFILE IMAGE
    // =====================================================

    public void sendProfileImageUploadedEmail(String to,
                                              String employeeName) {

        sendEmployeeNotification(
                to,
                employeeName,
                "Profile Picture Updated",
                "Your profile picture has been uploaded successfully."
        );
    }

    // =====================================================
    // EMPLOYEE UPDATED
    // =====================================================

    public void sendEmployeeUpdatedEmail(String to,
                                         String employeeName) {

        sendEmployeeNotification(
                to,
                employeeName,
                "Employee Updated",
                "Your employee details have been updated successfully."
        );
    }

    // =====================================================
    // EMPLOYEE DELETED
    // =====================================================

    public void sendEmployeeDeletedEmail(String to,
                                         String employeeName) {

        sendEmployeeNotification(
                to,
                employeeName,
                "Employee Deleted",
                "Your employee account has been deleted."
        );
    }

    // =====================================================
    // LEAVE APPLIED
    // =====================================================

    public void sendLeaveAppliedEmail(String to,
                                      String employeeName) {

        sendEmployeeNotification(
                to,
                employeeName,
                "Leave Request Submitted",
                "Your leave request has been submitted.\nCurrent Status : PENDING"
        );
    }

    // =====================================================
    // LEAVE APPROVED
    // =====================================================

    public void sendLeaveApprovedEmail(String to,
                                       String employeeName) {

        sendEmployeeNotification(
                to,
                employeeName,
                "Leave Approved",
                "Congratulations!\nYour leave request has been approved."
        );
    }

    // =====================================================
    // LEAVE REJECTED
    // =====================================================

    public void sendLeaveRejectedEmail(String to,
                                       String employeeName) {

        sendEmployeeNotification(
                to,
                employeeName,
                "Leave Rejected",
                "Your leave request has been rejected.\nPlease contact your manager."
        );
    }

}