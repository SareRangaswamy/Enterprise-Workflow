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

    // ===============================
    // Generic Email
    // ===============================
    public void sendEmail(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    // ===============================
    // Generic Employee Notification
    // ===============================
    public void sendEmployeeNotification(
            String to,
            String employeeName,
            String subject,
            String message) {

        sendEmail(
                to,
                subject,
                "Hello " + employeeName + ",\n\n"
                        + message
                        + "\n\nThank you,\nEnterprise Workflow Team"
        );
    }

    // ===============================
    // Welcome Email
    // ===============================
    public void sendWelcomeEmail(
            String to,
            String employeeName) {

        sendEmployeeNotification(
                to,
                employeeName,
                "Welcome to Enterprise Workflow",
                "Welcome to Enterprise Workflow!\n"
                        + "Your employee account has been created successfully."
        );
    }

    // ===============================
    // Profile Image Uploaded
    // ===============================
    public void sendProfileImageUploadedEmail(
            String to,
            String employeeName) {

        sendEmployeeNotification(
                to,
                employeeName,
                "Profile Picture Updated",
                "Your profile picture has been uploaded successfully."
        );
    }

    // ===============================
    // Employee Updated
    // ===============================
    public void sendEmployeeUpdatedEmail(
            String to,
            String employeeName) {

        sendEmployeeNotification(
                to,
                employeeName,
                "Employee Details Updated",
                "Your employee details have been updated successfully."
        );
    }

    // ===============================
    // Employee Deleted
    // ===============================
    public void sendEmployeeDeletedEmail(
            String to,
            String employeeName) {

        sendEmployeeNotification(
                to,
                employeeName,
                "Employee Account Deleted",
                "Your employee account has been deleted successfully."
        );
    }

    // ===============================
    // Leave Applied
    // ===============================
    public void sendLeaveAppliedEmail(
            String to,
            String employeeName) {

        sendEmployeeNotification(
                to,
                employeeName,
                "Leave Request Submitted",
                "Your leave request has been submitted successfully.\n"
                        + "Current Status : PENDING"
        );
    }

    // ===============================
    // Leave Approved
    // ===============================
    public void sendLeaveApprovedEmail(
            String to,
            String employeeName) {

        sendEmployeeNotification(
                to,
                employeeName,
                "Leave Approved",
                "Congratulations!\n"
                        + "Your leave request has been APPROVED."
        );
    }

    // ===============================
    // Leave Rejected
    // ===============================
    public void sendLeaveRejectedEmail(
            String to,
            String employeeName) {

        sendEmployeeNotification(
                to,
                employeeName,
                "Leave Rejected",
                "We regret to inform you that your leave request has been REJECTED.\n"
                        + "Please contact your manager for more details."
        );
    }
}