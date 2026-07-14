package com.ranga.Enterprise_workflow.repository;

import com.ranga.Enterprise_workflow.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Dashboard Counts
    long countByStatus(String status);

    long countByPriority(String priority);

    long countByStatusAndPriority(String status, String priority);

    // Search APIs
    List<Task> findByStatusContainingIgnoreCase(String status);

    List<Task> findByPriorityContainingIgnoreCase(String priority);

    List<Task> findByEmployeeId(Long employeeId);

    List<Task> findByProjectId(Long projectId);
}