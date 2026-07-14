package com.ranga.Enterprise_workflow.service;

import com.ranga.Enterprise_workflow.entity.Employee;
import com.ranga.Enterprise_workflow.exception.ResourceNotFoundException;
import com.ranga.Enterprise_workflow.repository.Departmentrepository;
import com.ranga.Enterprise_workflow.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ranga.Enterprise_workflow.entity.Department;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private Departmentrepository departmentRepository;

    @InjectMocks
    private Employeeservice employeeService;

    // Test: Employee Found
    @Test
    void testGetEmployeeById() {

        // Arrange
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setEmployeeName("Ranga Swamy");
        employee.setEmail("ranga@gmail.com");
        employee.setPhone("9876543210");
        employee.setDesignation("Software Engineer");
        employee.setSalary(60000.0);

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        // Act
        Employee result = employeeService.getEmployeeById(1L);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("Ranga Swamy", result.getEmployeeName());
        assertEquals("ranga@gmail.com", result.getEmail());
        assertEquals("Software Engineer", result.getDesignation());
    }

    // Test: Employee Not Found
    @Test
    void testGetEmployeeById_NotFound() {

        // Arrange
        when(employeeRepository.findById(100L))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> employeeService.getEmployeeById(100L)
        );

        assertEquals("Employee not found with ID: 100", exception.getMessage());
    }

    @Test
    void testSaveEmployee() {

        // Arrange
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("IT");

        Employee employee = new Employee();
        employee.setEmployeeName("Ranga Swamy");
        employee.setEmail("ranga@gmail.com");
        employee.setPhone("9876543210");
        employee.setDesignation("Software Engineer");
        employee.setSalary(60000.0);
        employee.setDepartment(department);

        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        // Act
        Employee savedEmployee = employeeService.saveEmployee(Employee);

        // Assert
        assertEquals("Ranga Swamy", savedEmployee.getEmployeeName());
        assertEquals("IT", savedEmployee.getDepartment().getDepartmentName());

        verify(departmentRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(employee);
    }
}