package com.ranga.Enterprise_workflow.repository;

import com.ranga.Enterprise_workflow.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByEmployeeNameContainingIgnoreCase(String employeeName);

    List<Employee> findByDesignationContainingIgnoreCase(String designation);

    List<Employee> findByDepartmentId(Long departmentId);

}