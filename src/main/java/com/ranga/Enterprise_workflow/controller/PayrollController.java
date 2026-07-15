package com.ranga.Enterprise_workflow.controller;

import com.ranga.Enterprise_workflow.entity.Payroll;
import com.ranga.Enterprise_workflow.service.PayrollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payroll")
@Tag(name = "Payroll API", description = "Payroll Management APIs")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @Operation(summary = "Generate Payroll")
    @ApiResponse(responseCode = "200", description = "Payroll generated successfully")
    @PostMapping("/{employeeId}")
    public Payroll generatePayroll(
            @PathVariable Long employeeId,
            @RequestBody Payroll payroll) {

        return payrollService.generatePayroll(employeeId, payroll);
    }

    @Operation(summary = "Get All Payroll")
    @GetMapping
    public List<Payroll> getAllPayroll() {
        return payrollService.getAllPayroll();
    }

    @Operation(summary = "Get Payroll By ID")
    @GetMapping("/{id}")
    public Payroll getPayrollById(@PathVariable Long id) {
        return payrollService.getPayrollById(id);
    }

    @Operation(summary = "Get Payroll By Employee")
    @GetMapping("/employee/{employeeId}")
    public List<Payroll> getPayrollByEmployee(@PathVariable Long employeeId) {
        return payrollService.getPayrollByEmployee(employeeId);
    }

    @Operation(summary = "Delete Payroll")
    @DeleteMapping("/{id}")
    public String deletePayroll(@PathVariable Long id) {

        payrollService.deletePayroll(id);

        return "Payroll deleted successfully";
    }
}