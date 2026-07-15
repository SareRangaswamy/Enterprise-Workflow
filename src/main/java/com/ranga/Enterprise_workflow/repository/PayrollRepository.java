package com.ranga.Enterprise_workflow.repository;

import com.ranga.Enterprise_workflow.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    List<Payroll> findByEmployeeId(Long employeeId);

}