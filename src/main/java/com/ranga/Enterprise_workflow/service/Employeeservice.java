package com.ranga.Enterprise_workflow.service;

import com.ranga.Enterprise_workflow.dto.EmployeeRequestDto;
import com.ranga.Enterprise_workflow.dto.EmployeeResponseDto;
import com.ranga.Enterprise_workflow.entity.Department;
import com.ranga.Enterprise_workflow.entity.Employee;
import com.ranga.Enterprise_workflow.exception.ResourceNotFoundException;
import com.ranga.Enterprise_workflow.file.S3FileStorageService;
import com.ranga.Enterprise_workflow.repository.Departmentrepository;
import com.ranga.Enterprise_workflow.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class Employeeservice {

    private static final Logger logger =
            LoggerFactory.getLogger(Employeeservice.class);

    private final EmployeeRepository employeeRepository;
    private final Departmentrepository departmentRepository;
    private final EmailService emailService;
    private final S3FileStorageService s3FileStorageService;

    public Employeeservice(EmployeeRepository employeeRepository,
                           Departmentrepository departmentRepository,
                           EmailService emailService,
                           S3FileStorageService s3FileStorageService) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.emailService = emailService;
        this.s3FileStorageService = s3FileStorageService;
    }

    // ===========================
    // CREATE EMPLOYEE
    // ===========================
    public EmployeeResponseDto saveEmployee(EmployeeRequestDto dto) {

        logger.info("Creating employee: {}", dto.getEmployeeName());

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with ID: " + dto.getDepartmentId()));

        Employee employee = new Employee();

        employee.setEmployeeName(dto.getEmployeeName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDesignation(dto.getDesignation());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);

        logger.info("Employee created successfully with ID: {}", savedEmployee.getId());

        emailService.sendWelcomeEmail(
                savedEmployee.getEmail(),
                savedEmployee.getEmployeeName()
        );

        return mapToResponse(savedEmployee);
    }

    // ===========================
    // GET ALL EMPLOYEES
    // ===========================
    public Page<Employee> getAllEmployees(int page,
                                          int size,
                                          String sortBy,
                                          String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository.findAll(pageable);
    }

    // ===========================
    // SEARCH
    // ===========================
    public List<Employee> searchEmployeeByName(String name) {

        return employeeRepository.findByEmployeeNameContainingIgnoreCase(name);
    }

    // ===========================
    // GET BY ID
    // ===========================
    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with ID: " + id));
    }

    // ===========================
    // UPDATE
    // ===========================
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existingEmployee = getEmployeeById(id);

        existingEmployee.setEmployeeName(employee.getEmployeeName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setDesignation(employee.getDesignation());
        existingEmployee.setSalary(employee.getSalary());

        Department department = departmentRepository.findById(
                        employee.getDepartment().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with ID: "
                                        + employee.getDepartment().getId()));

        existingEmployee.setDepartment(department);

        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        emailService.sendEmployeeUpdatedEmail(
                updatedEmployee.getEmail(),
                updatedEmployee.getEmployeeName()
        );

        return updatedEmployee;
    }

    // ===========================
    // FILTER DESIGNATION
    // ===========================
    public List<Employee> filterByDesignation(String designation) {

        return employeeRepository.findByDesignationContainingIgnoreCase(designation);
    }

    // ===========================
    // FILTER DEPARTMENT
    // ===========================
    public List<Employee> filterByDepartment(Long departmentId) {

        return employeeRepository.findByDepartmentId(departmentId);
    }

    // ===========================
    // PROFILE IMAGE
    // ===========================
    public String uploadProfileImage(Long employeeId,
                                     MultipartFile file) throws IOException {

        Employee employee = getEmployeeById(employeeId);

        String imageUrl = s3FileStorageService.uploadFile(file);

        employee.setProfileImageUrl(imageUrl);

        employeeRepository.save(employee);

        emailService.sendProfileImageUploadedEmail(
                employee.getEmail(),
                employee.getEmployeeName()
        );

        return imageUrl;
    }

    // ===========================
    // DELETE
    // ===========================
    public void deleteEmployee(Long id) {

        Employee employee = getEmployeeById(id);

        emailService.sendEmployeeDeletedEmail(
                employee.getEmail(),
                employee.getEmployeeName()
        );

        employeeRepository.delete(employee);
    }

    // ===========================
    // DTO MAPPING
    // ===========================
    private EmployeeResponseDto mapToResponse(Employee employee) {

        EmployeeResponseDto dto = new EmployeeResponseDto();

        dto.setId(employee.getId());
        dto.setEmployeeName(employee.getEmployeeName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setDesignation(employee.getDesignation());
        dto.setSalary(employee.getSalary());

        dto.setDepartmentId(employee.getDepartment().getId());
        dto.setDepartmentName(employee.getDepartment().getDepartmentName());

        return dto;
    }
}