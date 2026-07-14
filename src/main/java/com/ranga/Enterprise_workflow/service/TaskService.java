package com.ranga.Enterprise_workflow.service;

import com.ranga.Enterprise_workflow.entity.Task;

import java.util.List;

public interface TaskService {

    Task saveTask(Task task);

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

    List<Task> getTasksByStatus(String status);

    List<Task> getTasksByPriority(String priority);

    List<Task> getTasksByEmployee(Long employeeId);

    List<Task> getTasksByProject(Long projectId);
}