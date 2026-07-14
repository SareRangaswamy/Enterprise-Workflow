package com.ranga.Enterprise_workflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Health Check API",
        description = "API Health Check REST Endpoint"
)
@RestController
public class Testcontroller {

    @Operation(summary = "Check API Status")
    @ApiResponse(
            responseCode = "200",
            description = "API is running successfully"
    )
    @GetMapping("/api/test")
    public String test() {
        return "Enterprise Workflow API is running!";
    }
}