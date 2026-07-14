package com.ranga.Enterprise_workflow.service;

import com.ranga.Enterprise_workflow.dto.DashboardResponse;
import com.ranga.Enterprise_workflow.repository.Departmentrepository;
import com.ranga.Enterprise_workflow.repository.EmployeeRepository;
import com.ranga.Enterprise_workflow.repository.ProjectRepository;
import com.ranga.Enterprise_workflow.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final EmployeeRepository employeeRepository;
    private final Departmentrepository departmentRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public DashboardService(EmployeeRepository employeeRepository,
                            Departmentrepository departmentRepository,
                            ProjectRepository projectRepository,
                            TaskRepository taskRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }
    public DashboardResponse getDashboardDetails() {

        DashboardResponse response = new DashboardResponse();

        response.setTotalEmployees(employeeRepository.count());

        response.setTotalDepartments(departmentRepository.count());

        response.setTotalProjects(projectRepository.count());

        response.setTotalTasks(taskRepository.count());

        response.setCompletedTasks(taskRepository.countByStatus("COMPLETED"));

        response.setPendingTasks(taskRepository.countByStatus("PENDING"));

        response.setHighPriorityTasks(taskRepository.countByPriority("HIGH"));

        response.setLowPriorityTasks(taskRepository.countByPriority("LOW"));

        return response;
    }
}