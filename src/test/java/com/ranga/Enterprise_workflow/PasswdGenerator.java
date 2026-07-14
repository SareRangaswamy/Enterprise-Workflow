package com.ranga.Enterprise_workflow;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswdGenerator {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println(encoder.encode("admin123"));
    }
}