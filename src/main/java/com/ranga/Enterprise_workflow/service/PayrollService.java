package com.ranga.Enterprise_workflow.service;

import com.ranga.Enterprise_workflow.entity.Employee;
import com.ranga.Enterprise_workflow.entity.Payroll;
import com.ranga.Enterprise_workflow.exception.ResourceNotFoundException;
import com.ranga.Enterprise_workflow.repository.EmployeeRepository;
import com.ranga.Enterprise_workflow.repository.PayrollRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    public PayrollService(PayrollRepository payrollRepository,
                          EmployeeRepository employeeRepository,
                          EmailService emailService) {

        this.payrollRepository = payrollRepository;
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
    }

    // Generate Payroll
    public Payroll generatePayroll(Long employeeId, Payroll payroll) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        payroll.setEmployee(employee);

        // Use employee salary if basic salary is not provided
        if (payroll.getBasicSalary() == null) {
            payroll.setBasicSalary(employee.getSalary());
        }

        if (payroll.getBonus() == null) {
            payroll.setBonus(0.0);
        }

        if (payroll.getDeductions() == null) {
            payroll.setDeductions(0.0);
        }

        double netSalary = payroll.getBasicSalary()
                + payroll.getBonus()
                - payroll.getDeductions();

        payroll.setNetSalary(netSalary);
        payroll.setPaymentDate(LocalDate.now());

        Payroll savedPayroll = payrollRepository.save(payroll);

        // Send salary email
        emailService.sendEmail(
                employee.getEmail(),
                "Salary Credited",
                "Hello " + employee.getEmployeeName()
                        + ",\n\nYour salary has been processed successfully."
                        + "\n\nBasic Salary : ₹" + payroll.getBasicSalary()
                        + "\nBonus : ₹" + payroll.getBonus()
                        + "\nDeductions : ₹" + payroll.getDeductions()
                        + "\nNet Salary : ₹" + payroll.getNetSalary()
                        + "\nPayment Date : " + payroll.getPaymentDate()
                        + "\n\nRegards,\nEnterprise Workflow Team"
        );

        return savedPayroll;
    }

    // Get All Payroll
    public List<Payroll> getAllPayroll() {
        return payrollRepository.findAll();
    }

    // Get Payroll By ID
    public Payroll getPayrollById(Long id) {
        return payrollRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payroll not found"));
    }

    // Get Payroll By Employee
    public List<Payroll> getPayrollByEmployee(Long employeeId) {
        return payrollRepository.findByEmployeeId(employeeId);
    }

    // Delete Payroll
    public void deletePayroll(Long id) {

        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payroll not found"));

        payrollRepository.delete(payroll);
    }
}