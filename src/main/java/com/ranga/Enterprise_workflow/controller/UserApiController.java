package com.ranga.Enterprise_workflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "User API",
        description = "User Access REST APIs"
)
@RestController
@RequestMapping("/api")
public class UserApiController {

    @Operation(summary = "User Welcome API")
    @ApiResponse(
            responseCode = "200",
            description = "User API accessed successfully"
    )
    @GetMapping("/user")
    public String user() {
        return "Welcome User";
    }
}