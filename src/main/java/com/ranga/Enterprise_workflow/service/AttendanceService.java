package com.ranga.Enterprise_workflow.service;

import com.ranga.Enterprise_workflow.entity.Attendance;
import com.ranga.Enterprise_workflow.entity.Employee;
import com.ranga.Enterprise_workflow.exception.ResourceNotFoundException;
import com.ranga.Enterprise_workflow.repository.AttendanceRepository;
import com.ranga.Enterprise_workflow.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    public AttendanceService(
            AttendanceRepository attendanceRepository,
            EmployeeRepository employeeRepository,
            EmailService emailService) {

        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
    }

    // Employee Check-In
    public Attendance checkIn(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        if (attendanceRepository
                .findByEmployeeIdAndAttendanceDate(employeeId, LocalDate.now())
                .isPresent()) {

            throw new RuntimeException("Attendance already marked for today");
        }

        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setAttendanceDate(LocalDate.now());
        attendance.setCheckInTime(LocalTime.now());
        attendance.setStatus("PRESENT");

        Attendance savedAttendance = attendanceRepository.save(attendance);

        emailService.sendEmail(
                employee.getEmail(),
                "Check-In Successful",
                "Hello " + employee.getEmployeeName()
                        + ",\n\nYour attendance has been marked successfully."
                        + "\nCheck-In Time: " + savedAttendance.getCheckInTime()
                        + "\n\nRegards,\nEnterprise Workflow Team"
        );

        return savedAttendance;
    }

    // Employee Check-Out
    public Attendance checkOut(Long employeeId) {

        Attendance attendance = attendanceRepository
                .findByEmployeeIdAndAttendanceDate(employeeId, LocalDate.now())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Attendance not found for today"));

        attendance.setCheckOutTime(LocalTime.now());

        Attendance updatedAttendance = attendanceRepository.save(attendance);

        emailService.sendEmail(
                attendance.getEmployee().getEmail(),
                "Check-Out Successful",
                "Hello " + attendance.getEmployee().getEmployeeName()
                        + ",\n\nYou have checked out successfully."
                        + "\nCheck-Out Time: " + updatedAttendance.getCheckOutTime()
                        + "\n\nRegards,\nEnterprise Workflow Team"
        );

        return updatedAttendance;
    }

    // Get All Attendance
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    // Get Attendance By ID
    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Attendance not found"));
    }

    // Get Attendance By Employee
    public List<Attendance> getAttendanceByEmployee(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    // Get Attendance By Date
    public List<Attendance> getAttendanceByDate(LocalDate attendanceDate) {
        return attendanceRepository.findByAttendanceDate(attendanceDate);
    }

    // Delete Attendance
    public void deleteAttendance(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Attendance not found"));

        attendanceRepository.delete(attendance);
    }
}