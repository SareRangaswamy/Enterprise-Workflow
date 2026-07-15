package com.ranga.Enterprise_workflow.service;

import com.ranga.Enterprise_workflow.dto.DashboardResponse;
import com.ranga.Enterprise_workflow.repository.AttendanceRepository;
import com.ranga.Enterprise_workflow.repository.Departmentrepository;
import com.ranga.Enterprise_workflow.repository.EmployeeRepository;
import com.ranga.Enterprise_workflow.repository.Leaverepository;
import com.ranga.Enterprise_workflow.repository.PayrollRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {

    private final EmployeeRepository employeeRepository;
    private final Departmentrepository departmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final Leaverepository leaveRepository;
    private final PayrollRepository payrollRepository;

    public DashboardService(EmployeeRepository employeeRepository,
                            Departmentrepository departmentRepository,
                            AttendanceRepository attendanceRepository,
                            Leaverepository leaveRepository,
                            PayrollRepository payrollRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.attendanceRepository = attendanceRepository;
        this.leaveRepository = leaveRepository;
        this.payrollRepository = payrollRepository;
    }

    public DashboardResponse getDashboardDetails() {

        DashboardResponse response = new DashboardResponse();

        response.setTotalEmployees(employeeRepository.count());

        response.setTotalDepartments(departmentRepository.count());

        response.setPresentToday(
                attendanceRepository.findByAttendanceDate(LocalDate.now()).size());

        response.setEmployeesOnLeave(
                leaveRepository.countByStatus("APPROVED"));

        response.setTotalPayrolls(
                payrollRepository.count());

        return response;
    }
}