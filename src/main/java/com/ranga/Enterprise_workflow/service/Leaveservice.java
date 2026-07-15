package com.ranga.Enterprise_workflow.service;

import com.ranga.Enterprise_workflow.entity.Leave;
import com.ranga.Enterprise_workflow.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    // Apply Leave
    public Leave applyLeave(Leave leave) {

        leave.setStatus("PENDING");

        return leaveRepository.save(leave);
    }

    // Get All Leaves
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    // Get Leave By ID
    public Leave getLeaveById(Long id) {

        return leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
    }

    // Approve Leave
    public Leave approveLeave(Long id) {

        Leave leave = getLeaveById(id);

        leave.setStatus("APPROVED");

        return leaveRepository.save(leave);
    }

    // Reject Leave
    public Leave rejectLeave(Long id) {

        Leave leave = getLeaveById(id);

        leave.setStatus("REJECTED");

        return leaveRepository.save(leave);
    }

    // Delete Leave
    public void deleteLeave(Long id) {

        Leave leave = getLeaveById(id);

        leaveRepository.delete(leave);
    }
}