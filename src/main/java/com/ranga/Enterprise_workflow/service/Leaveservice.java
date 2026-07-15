package com.ranga.Enterprise_workflow.service;

import com.ranga.Enterprise_workflow.entity.Employee;
import com.ranga.Enterprise_workflow.entity.Leave;
import com.ranga.Enterprise_workflow.repository.EmployeeRepository;
import com.ranga.Enterprise_workflow.repository.Leaverepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Leaveservice {

    private final Leaverepository leaverepository;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    public Leaveservice(Leaverepository leaveRepository,
                        EmployeeRepository employeeRepository,
                        EmailService emailService) {

        this.leaverepository =leaveRepository;
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
    }

    // Apply Leave
    public Leave applyLeave(Leave leave) {

        Employee employee = employeeRepository.findById(
                leave.getEmployee().getId()
        ).orElseThrow(() -> new RuntimeException("Employee not found"));

        leave.setEmployee(employee);
        leave.setStatus("PENDING");

        Leave savedLeave = leaverepository.save(leave);

        emailService.sendLeaveAppliedEmail(
                employee.getEmail(),
                employee.getEmployeeName()
        );

        return savedLeave;
    }

    // Get All Leaves
    public List<Leave> getAllLeaves() {
        return leaverepository.findAll();
    }

    // Get Leave By ID
    public Leave getLeaveById(Long id) {
        return leaverepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
    }

    // Approve Leave
    public Leave approveLeave(Long id) {

        Leave leave = getLeaveById(id);

        leave.setStatus("APPROVED");

        Leave updatedLeave = leaverepository.save(leave);

        emailService.sendLeaveApprovedEmail(
                updatedLeave.getEmployee().getEmail(),
                updatedLeave.getEmployee().getEmployeeName()
        );

        return updatedLeave;
    }

    // Reject Leave
    public Leave rejectLeave(Long id) {

        Leave leave = getLeaveById(id);

        leave.setStatus("REJECTED");

        Leave updatedLeave = leaverepository.save(leave);

        emailService.sendLeaveRejectedEmail(
                updatedLeave.getEmployee().getEmail(),
                updatedLeave.getEmployee().getEmployeeName()
        );

        return updatedLeave;
    }

    // Delete Leave
    public void deleteLeave(Long id) {

        Leave leave = getLeaveById(id);

        leaverepository.delete(leave);
    }
}