package com.ranga.Enterprise_workflow.controller;

import com.ranga.Enterprise_workflow.entity.Department;
import com.ranga.Enterprise_workflow.service.Departmentservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Department API",
        description = "Department Management REST APIs"
)
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final Departmentservice departmentService;

    public DepartmentController(Departmentservice departmentService) {
        this.departmentService = departmentService;
    }

    // Create Department
    @Operation(summary = "Create Department")
    @ApiResponse(
            responseCode = "201",
            description = "Department created successfully"
    )
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    // Get All Departments
    @Operation(summary = "Get All Departments")
    @ApiResponse(
            responseCode = "200",
            description = "Departments fetched successfully"
    )
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // Get Department By ID
    @Operation(summary = "Get Department By ID")
    @ApiResponse(
            responseCode = "200",
            description = "Department fetched successfully"
    )
    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    // Update Department
    @Operation(summary = "Update Department")
    @ApiResponse(
            responseCode = "200",
            description = "Department updated successfully"
    )
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id,
                                       @RequestBody Department department) {
        return departmentService.updateDepartment(id, department);
    }

    // Delete Department
    @Operation(summary = "Delete Department")
    @ApiResponse(
            responseCode = "200",
            description = "Department deleted successfully"
    )
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "Department deleted successfully";
    }
}