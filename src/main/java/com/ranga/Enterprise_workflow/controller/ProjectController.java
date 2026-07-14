package com.ranga.Enterprise_workflow.controller;

import com.ranga.Enterprise_workflow.entity.Project;
import com.ranga.Enterprise_workflow.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Project API",
        description = "Project Management REST APIs"
)
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Create Project
    @Operation(summary = "Create Project")
    @ApiResponse(
            responseCode = "201",
            description = "Project created successfully"
    )
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    // Get All Projects
    @Operation(summary = "Get All Projects")
    @ApiResponse(
            responseCode = "200",
            description = "Projects fetched successfully"
    )
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // Get Project By ID
    @Operation(summary = "Get Project By ID")
    @ApiResponse(
            responseCode = "200",
            description = "Project fetched successfully"
    )
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    // Update Project
    @Operation(summary = "Update Project")
    @ApiResponse(
            responseCode = "200",
            description = "Project updated successfully"
    )
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id,
                                 @RequestBody Project project) {
        return projectService.updateProject(id, project);
    }

    // Delete Project
    @Operation(summary = "Delete Project")
    @ApiResponse(
            responseCode = "200",
            description = "Project deleted successfully"
    )
    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "Project deleted successfully";
    }
}