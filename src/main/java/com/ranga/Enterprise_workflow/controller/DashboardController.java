package com.ranga.Enterprise_workflow.controller;

import com.ranga.Enterprise_workflow.dto.DashboardResponse;
import com.ranga.Enterprise_workflow.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Dashboard API",
        description = "Dashboard Statistics REST APIs"
)
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @Operation(summary = "Get Dashboard Statistics")
    @ApiResponse(
            responseCode = "200",
            description = "Dashboard statistics fetched successfully"
    )
    @GetMapping
    public DashboardResponse getDashboard() {
        return dashboardService.getDashboardDetails();
    }
}