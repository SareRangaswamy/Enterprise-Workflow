package com.ranga.Enterprise_workflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Admin API",
        description = "Admin Access REST APIs"
)
@RestController
@RequestMapping("/api")
public class Admincontroller {

    @Operation(summary = "Admin Welcome API")
    @ApiResponse(
            responseCode = "200",
            description = "Admin API accessed successfully"
    )
    @GetMapping("/admin")
    public String admin() {
        return "Welcome Admin";
    }
}