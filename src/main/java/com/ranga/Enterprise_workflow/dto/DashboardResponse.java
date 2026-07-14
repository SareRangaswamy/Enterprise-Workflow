package com.ranga.Enterprise_workflow.dto;

public class DashboardResponse {
    private long highPriorityTasks;

    private long lowPriorityTasks;

    private long totalEmployees;
    private long totalDepartments;
    private long totalProjects;
    private long totalTasks;
    private long completedTasks;
    private long pendingTasks;

    public DashboardResponse() {
    }

    public DashboardResponse(long totalEmployees,
                             long totalDepartments,
                             long totalProjects,
                             long totalTasks,
                             long completedTasks,
                             long pendingTasks) {
        this.totalEmployees = totalEmployees;
        this.totalDepartments = totalDepartments;
        this.totalProjects = totalProjects;
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.pendingTasks = pendingTasks;
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