package com.ranga.Enterprise_workflow.repository;

import com.ranga.Enterprise_workflow.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Leaverepository extends JpaRepository<Leave, Long> {

    List<Leave> findByEmployeeId(Long employeeId);

    List<Leave> findByStatus(String status);

    long countByStatus(String status);
}