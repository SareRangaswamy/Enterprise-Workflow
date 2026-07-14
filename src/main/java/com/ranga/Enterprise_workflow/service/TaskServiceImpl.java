package com.ranga.Enterprise_workflow.service;

import com.ranga.Enterprise_workflow.entity.Employee;
import com.ranga.Enterprise_workflow.entity.Task;
import com.ranga.Enterprise_workflow.repository.EmployeeRepository;
import com.ranga.Enterprise_workflow.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    public TaskServiceImpl(TaskRepository taskRepository,
                           EmployeeRepository employeeRepository,
                           EmailService emailService) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
    }

    @Override
    public Task saveTask(Task task) {

        Employee employee = employeeRepository.findById(task.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        task.setEmployee(employee);

        Task savedTask = taskRepository.save(task);

        String subject = "New Task Assigned";

        String body = "Hello " + employee.getEmployeeName() + ",\n\n"
                + "A new task has been assigned to you.\n\n"
                + "Task Name: " + savedTask.getTaskName() + "\n"
                + "Priority: " + savedTask.getPriority() + "\n"
                + "Status: " + savedTask.getStatus() + "\n"
                + "Due Date: " + savedTask.getDueDate() + "\n\n"
                + "Regards,\nAdmin";

        try {
            emailService.sendEmail(
                    employee.getEmail(),
                    subject,
                    body
            );
            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Task created, but email could not be sent.");
        }
        return savedTask;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public Task updateTask(Long id, Task task) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setTaskName(task.getTaskName());
        existingTask.setDescription(task.getDescription());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setDueDate(task.getDueDate());

        if (task.getEmployee() != null) {
            Employee employee = employeeRepository.findById(task.getEmployee().getId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            existingTask.setEmployee(employee);
        }

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatusContainingIgnoreCase(status);
    }

    @Override
    public List<Task> getTasksByPriority(String priority) {
        return taskRepository.findByPriorityContainingIgnoreCase(priority);
    }

    @Override
    public List<Task> getTasksByEmployee(Long employeeId) {
        return taskRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<Task> getTasksByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }
}