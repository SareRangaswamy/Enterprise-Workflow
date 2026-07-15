package com.ranga.Enterprise_workflow.controller;

import com.ranga.Enterprise_workflow.entity.Leave;
import com.ranga.Enterprise_workflow.service.Leaveservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Leave API",
        description = "Leave Management REST APIs"
)
@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final Leaveservice leaveService;

    public LeaveController(Leaveservice leaveService) {
        this.leaveService = leaveService;
    }

    // Apply Leave
    @Operation(summary = "Apply Leave")
    @ApiResponse(
            responseCode = "201",
            description = "Leave applied successfully"
    )
    @PostMapping
    public Leave applyLeave(@RequestBody Leave leave) {
        return leaveService.applyLeave(leave);
    }

    // Get All Leaves
    @Operation(summary = "Get All Leave Requests")
    @ApiResponse(
            responseCode = "200",
            description = "Leave requests fetched successfully"
    )
    @GetMapping
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    // Get Leave By ID
    @Operation(summary = "Get Leave By ID")
    @ApiResponse(
            responseCode = "200",
            description = "Leave fetched successfully"
    )
    @GetMapping("/{id}")
    public Leave getLeaveById(@PathVariable Long id) {
        return leaveService.getLeaveById(id);
    }

    // Approve Leave
    @Operation(summary = "Approve Leave")
    @ApiResponse(
            responseCode = "200",
            description = "Leave approved successfully"
    )
    @PutMapping("/{id}/approve")
    public Leave approveLeave(@PathVariable Long id) {
        return leaveService.approveLeave(id);
    }

    // Reject Leave
    @Operation(summary = "Reject Leave")
    @ApiResponse(
            responseCode = "200",
            description = "Leave rejected successfully"
    )
    @PutMapping("/{id}/reject")
    public Leave rejectLeave(@PathVariable Long id) {
        return leaveService.rejectLeave(id);
    }

    // Delete Leave
    @Operation(summary = "Delete Leave")
    @ApiResponse(
            responseCode = "200",
            description = "Leave deleted successfully"
    )
    @DeleteMapping("/{id}")
    public String deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
        return "Leave deleted successfully";
    }
}