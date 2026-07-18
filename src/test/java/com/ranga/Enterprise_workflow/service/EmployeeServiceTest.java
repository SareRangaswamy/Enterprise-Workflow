package com.ranga.Enterprise_workflow.service;

import com.ranga.Enterprise_workflow.dto.EmployeeRequestDto;
import com.ranga.Enterprise_workflow.dto.EmployeeResponseDto;
import com.ranga.Enterprise_workflow.entity.Department;
import com.ranga.Enterprise_workflow.entity.Employee;
import com.ranga.Enterprise_workflow.exception.ResourceNotFoundException;
import com.ranga.Enterprise_workflow.file.S3FileStorageService;
import com.ranga.Enterprise_workflow.repository.Departmentrepository;
import com.ranga.Enterprise_workflow.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private Departmentrepository departmentRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private S3FileStorageService s3FileStorageService;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testGetEmployeeById() {

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setEmployeeName("Ranga Swamy");
        employee.setEmail("ranga@gmail.com");
        employee.setPhone("9876543210");
        employee.setDesignation("Software Engineer");
        employee.setSalary(60000.0);

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Ranga Swamy", result.getEmployeeName());
        assertEquals("ranga@gmail.com", result.getEmail());
        assertEquals("Software Engineer", result.getDesignation());
    }

    @Test
    void testGetEmployeeById_NotFound() {

        when(employeeRepository.findById(100L))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> employeeService.getEmployeeById(100L));

        assertEquals("Employee not found with ID: 100",
                exception.getMessage());
    }

    @Test
    void testSaveEmployee() {

        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("IT");

        EmployeeRequestDto dto = new EmployeeRequestDto();
        dto.setEmployeeName("Ranga Swamy");
        dto.setEmail("ranga@gmail.com");
        dto.setPhone("9876543210");
        dto.setDesignation("Software Engineer");
        dto.setSalary(60000.0);
        dto.setDepartmentId(1L);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setEmployeeName("Ranga Swamy");
        employee.setEmail("ranga@gmail.com");
        employee.setPhone("9876543210");
        employee.setDesignation("Software Engineer");
        employee.setSalary(60000.0);
        employee.setDepartment(department);

        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));

        when(employeeRepository.save(ArgumentMatchers.any(Employee.class)))
                .thenReturn(employee);

        doNothing().when(emailService)
                .sendWelcomeEmail(anyString(), anyString());

        EmployeeResponseDto response =
                employeeService.saveEmployee(dto);

        assertEquals("Ranga Swamy", response.getEmployeeName());
        assertEquals("IT", response.getDepartmentName());

        verify(departmentRepository).findById(1L);
        verify(employeeRepository).save(any(Employee.class));
        verify(emailService).sendWelcomeEmail(anyString(), anyString());
    }
}