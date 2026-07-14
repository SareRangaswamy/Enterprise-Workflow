package com.ranga.Enterprise_workflow.controller;

import com.ranga.Enterprise_workflow.dto.EmployeeRequestDto;
import com.ranga.Enterprise_workflow.dto.EmployeeResponseDto;
import com.ranga.Enterprise_workflow.entity.Employee;
import com.ranga.Enterprise_workflow.service.Employeeservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(
        name = "Employee API",
        description = "Employee Management REST APIs"
)
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final Employeeservice employeeService;

    public EmployeeController(Employeeservice employeeService) {
        this.employeeService = employeeService;
    }

    // Create Employee
    @Operation(summary = "Create Employee")
    @ApiResponse(
            responseCode = "201",
            description = "Employee created successfully"
    )
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(
            @Valid @RequestBody EmployeeRequestDto dto) {

        return ResponseEntity.ok(employeeService.saveEmployee(dto));
    }

    // Upload Profile Image
    @Operation(summary = "Upload Employee Profile Image")
    @ApiResponse(
            responseCode = "200",
            description = "Profile image uploaded successfully"
    )
    @PostMapping("/{id}/profile-image")
    public ResponseEntity<String> uploadProfileImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws IOException {

        String imageUrl = employeeService.uploadProfileImage(id, file);

        return ResponseEntity.ok(imageUrl);
    }

    // Get All Employees
    @Operation(summary = "Get All Employees")
    @ApiResponse(
            responseCode = "200",
            description = "Employees fetched successfully"
    )
    @GetMapping
    public Page<Employee> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return employeeService.getAllEmployees(page, size, sortBy, direction);
    }

    // Search Employee By Name
    @Operation(summary = "Search Employees By Name")
    @ApiResponse(
            responseCode = "200",
            description = "Employees found successfully"
    )
    @GetMapping("/search")
    public List<Employee> searchEmployeeByName(
            @RequestParam String name) {

        return employeeService.searchEmployeeByName(name);
    }

    // Filter By Designation
    @Operation(summary = "Filter Employees By Designation")
    @ApiResponse(
            responseCode = "200",
            description = "Employees filtered successfully"
    )
    @GetMapping("/filter/designation")
    public List<Employee> filterByDesignation(
            @RequestParam String designation) {

        return employeeService.filterByDesignation(designation);
    }

    // Filter By Department
    @Operation(summary = "Filter Employees By Department")
    @ApiResponse(
            responseCode = "200",
            description = "Employees fetched successfully"
    )
    @GetMapping("/filter/department/{departmentId}")
    public List<Employee> filterByDepartment(
            @PathVariable Long departmentId) {

        return employeeService.filterByDepartment(departmentId);
    }

    // Get Employee By ID
    @Operation(summary = "Get Employee By ID")
    @ApiResponse(
            responseCode = "200",
            description = "Employee fetched successfully"
    )
    @GetMapping("/{id}")
    public Employee getEmployeeById(
            @PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }

    // Update Employee
    @Operation(summary = "Update Employee")
    @ApiResponse(
            responseCode = "200",
            description = "Employee updated successfully"
    )
    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody Employee employee) {

        return employeeService.updateEmployee(id, employee);
    }

    // Delete Employee
    @Operation(summary = "Delete Employee")
    @ApiResponse(
            responseCode = "200",
            description = "Employee deleted successfully"
    )
    @DeleteMapping("/{id}")
    public String deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "Employee deleted successfully";
    }
}