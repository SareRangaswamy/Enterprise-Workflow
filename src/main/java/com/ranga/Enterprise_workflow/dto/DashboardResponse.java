package com.ranga.Enterprise_workflow.dto;

public class DashboardResponse {

    private long totalEmployees;
    private long totalDepartments;
    private long presentToday;
    private long employeesOnLeave;
    private long totalPayrolls;

    private long totalProjects;
    private long totalTasks;
    private long completedTasks;
    private long pendingTasks;
    private long highPriorityTasks;
    private long lowPriorityTasks;

    public DashboardResponse() {
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public long getTotalDepartments() {
        return totalDepartments;
    }

    public void setTotalDepartments(long totalDepartments) {
        this.totalDepartments = totalDepartments;
    }

    public long getPresentToday() {
        return presentToday;
    }

    public void setPresentToday(long presentToday) {
        this.presentToday = presentToday;
    }

    public long getEmployeesOnLeave() {
        return employeesOnLeave;
    }

    public void setEmployeesOnLeave(long employeesOnLeave) {
        this.employeesOnLeave = employeesOnLeave;
    }

    public long getTotalPayrolls() {
        return totalPayrolls;
    }

    public void setTotalPayrolls(long totalPayrolls) {
        this.totalPayrolls = totalPayrolls;
    }

    public long getTotalProjects() {
        return totalProjects;
    }

    public void setTotalProjects(long totalProjects) {
        this.totalProjects = totalProjects;
    }

    public long getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(long totalTasks) {
        this.totalTasks = totalTasks;
    }

    public long getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(long completedTasks) {
        this.completedTasks = completedTasks;
    }

    public long getPendingTasks() {
        return pendingTasks;
    }

    public void setPendingTasks(long pendingTasks) {
        this.pendingTasks = pendingTasks;
    }

    public long getHighPriorityTasks() {
        return highPriorityTasks;
    }

    public void setHighPriorityTasks(long highPriorityTasks) {
        this.highPriorityTasks = highPriorityTasks;
    }

    public long getLowPriorityTasks() {
        return lowPriorityTasks;
    }

    public void setLowPriorityTasks(long lowPriorityTasks) {
        this.lowPriorityTasks = lowPriorityTasks;
    }
}