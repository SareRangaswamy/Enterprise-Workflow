package com.ranga.Enterprise_workflow.service;
import com.ranga.Enterprise_workflow.dto.EmployeeRequestDto;
import com.ranga.Enterprise_workflow.dto.EmployeeResponseDto;

import com.ranga.Enterprise_workflow.entity.Department;
import com.ranga.Enterprise_workflow.file.S3FileStorageService;
import com.ranga.Enterprise_workflow.entity.Employee;
import com.ranga.Enterprise_workflow.exception.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import com.ranga.Enterprise_workflow.repository.Departmentrepository;
import com.ranga.Enterprise_workflow.repository.EmployeeRepository;
import org.slf4j.Logger;

import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


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
    // create employee

     // Create Employee
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

         // Send Welcome Email
         emailService.sendEmail(
                 savedEmployee.getEmail(),
                 "Welcome to Enterprise Workflow",
                 "Hi " + savedEmployee.getEmployeeName()
                         + ",\n\n"
                         + "Welcome to Enterprise Workflow!\n"
                         + "Your employee account has been created successfully.\n\n"
                         + "Regards,\n"
                         + "Enterprise Workflow Team"
         );

         return mapToResponse(savedEmployee);
     }
    // Get All Employees with Pagination and Sorting
    public Page<Employee> getAllEmployees(int page,
                                          int size,
                                          String sortBy,
                                          String direction) {

        logger.info("Fetching employees - Page: {}, Size: {}, SortBy: {}, Direction: {}",
                page, size, sortBy, direction);

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository.findAll(pageable);
    }

    // Search Employee By Name
    public List<Employee> searchEmployeeByName(String name) {

        logger.info("Searching employees with name: {}", name);

        List<Employee> employees =
                employeeRepository.findByEmployeeNameContainingIgnoreCase(name);

        if (employees.isEmpty()) {
            logger.warn("No employees found with name: {}", name);
        }

        return employees;
    }

    // Get Employee By ID
    public Employee getEmployeeById(Long id) {

        logger.info("Fetching employee with ID: {}", id);

        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Employee not found with ID: {}", id);
                    return new ResourceNotFoundException("Employee not found with ID: " + id);
                });
    }

    // Update Employee
    public Employee updateEmployee(Long id, Employee employee) {

        logger.info("Updating employee with ID: {}", id);

        Employee existingEmployee = getEmployeeById(id);

        existingEmployee.setEmployeeName(employee.getEmployeeName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setDesignation(employee.getDesignation());
        existingEmployee.setSalary(employee.getSalary());

        Long departmentId = employee.getDepartment().getId();

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> {
                    logger.error("Department not found with ID: {}", departmentId);
                    return new ResourceNotFoundException("Department not found with ID: " + departmentId);
                });

        existingEmployee.setDepartment(department);

        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        logger.info("Employee updated successfully with ID: {}", updatedEmployee.getId());

        return updatedEmployee;
    }


    // Filter Employees By Designation
    public List<Employee> filterByDesignation(String designation) {

        logger.info("Filtering employees by designation: {}", designation);

        List<Employee> employees =
                employeeRepository.findByDesignationContainingIgnoreCase(designation);

        if (employees.isEmpty()) {
            logger.warn("No employees found with designation: {}", designation);
        }

        return employees;
    }

    // Filter Employees By Department
    public List<Employee> filterByDepartment(Long departmentId) {

        logger.info("Filtering employees by department ID: {}", departmentId);

        List<Employee> employees =
                employeeRepository.findByDepartmentId(departmentId);

        if (employees.isEmpty()) {
            logger.warn("No employees found for department ID: {}", departmentId);
        }

        return employees;
    }


    public String uploadProfileImage(Long employeeId, MultipartFile file) throws IOException {

        Employee employee = getEmployeeById(employeeId);

        // Upload image to AWS S3 and get the full URL
        String imageUrl = s3FileStorageService.uploadFile(file);

        // Save the URL in the employee table
        employee.setProfileImageUrl(imageUrl);

        employeeRepository.save(employee);

        return imageUrl;
    }


    // Delete Employee
    public void deleteEmployee(Long id) {

        logger.info("Deleting employee with ID: {}", id);

        Employee employee = getEmployeeById(id);

        employeeRepository.delete(employee);

        logger.info("Employee deleted successfully with ID: {}", id);
    }

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