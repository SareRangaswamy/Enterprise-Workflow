package com.ranga.Enterprise_workflow.controller;

import com.ranga.Enterprise_workflow.entity.Task;
import com.ranga.Enterprise_workflow.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Task API",
        description = "Task Management REST APIs"
)
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create Task
    @Operation(summary = "Create Task")
    @ApiResponse(
            responseCode = "201",
            description = "Task created successfully"
    )
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    // Get All Tasks
    @Operation(summary = "Get All Tasks")
    @ApiResponse(
            responseCode = "200",
            description = "Tasks fetched successfully"
    )
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Search Tasks By Status
    @Operation(summary = "Search Tasks By Status")
    @ApiResponse(
            responseCode = "200",
            description = "Tasks fetched successfully"
    )
    @GetMapping("/search/status")
    public List<Task> getTasksByStatus(@RequestParam String status) {
        return taskService.getTasksByStatus(status);
    }

    // Search Tasks By Priority
    @Operation(summary = "Search Tasks By Priority")
    @ApiResponse(
            responseCode = "200",
            description = "Tasks fetched successfully"
    )
    @GetMapping("/search/priority")
    public List<Task> getTasksByPriority(@RequestParam String priority) {
        return taskService.getTasksByPriority(priority);
    }

    // Get Tasks By Employee
    @Operation(summary = "Get Tasks By Employee")
    @ApiResponse(
            responseCode = "200",
            description = "Tasks fetched successfully"
    )
    @GetMapping("/search/employee")
    public List<Task> getTasksByEmployee(@RequestParam Long employeeId) {
        return taskService.getTasksByEmployee(employeeId);
    }

    // Get Tasks By Project
    @Operation(summary = "Get Tasks By Project")
    @ApiResponse(
            responseCode = "200",
            description = "Tasks fetched successfully"
    )
    @GetMapping("/search/project")
    public List<Task> getTasksByProject(@RequestParam Long projectId) {
        return taskService.getTasksByProject(projectId);
    }

    // Get Task By ID
    @Operation(summary = "Get Task By ID")
    @ApiResponse(
            responseCode = "200",
            description = "Task fetched successfully"
    )
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // Update Task
    @Operation(summary = "Update Task")
    @ApiResponse(
            responseCode = "200",
            description = "Task updated successfully"
    )
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,
                           @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    // Delete Task
    @Operation(summary = "Delete Task")
    @ApiResponse(
            responseCode = "200",
            description = "Task deleted successfully"
    )
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }
}