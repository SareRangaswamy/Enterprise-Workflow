package com.ranga.Enterprise_workflow.controller;

import com.ranga.Enterprise_workflow.entity.Attendance;
import com.ranga.Enterprise_workflow.service.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@Tag(name = "Attendance API", description = "Attendance Management APIs")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @Operation(summary = "Employee Check In")
    @ApiResponse(responseCode = "200", description = "Check-In successful")
    @PostMapping("/checkin/{employeeId}")
    public Attendance checkIn(@PathVariable Long employeeId) {
        return attendanceService.checkIn(employeeId);
    }

    @Operation(summary = "Employee Check Out")
    @ApiResponse(responseCode = "200", description = "Check-Out successful")
    @PostMapping("/checkout/{employeeId}")
    public Attendance checkOut(@PathVariable Long employeeId) {
        return attendanceService.checkOut(employeeId);
    }

    @Operation(summary = "Get All Attendance")
    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    @Operation(summary = "Get Attendance By ID")
    @GetMapping("/{id}")
    public Attendance getAttendanceById(@PathVariable Long id) {
        return attendanceService.getAttendanceById(id);
    }

    @Operation(summary = "Get Attendance By Employee")
    @GetMapping("/employee/{employeeId}")
    public List<Attendance> getAttendanceByEmployee(@PathVariable Long employeeId) {
        return attendanceService.getAttendanceByEmployee(employeeId);
    }

    @Operation(summary = "Delete Attendance")
    @DeleteMapping("/{id}")
    public String deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return "Attendance deleted successfully";
    }
}