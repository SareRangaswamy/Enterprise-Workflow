# 🚀 Enterprise Workflow & Collaboration Platform

A secure and scalable Enterprise Workflow & Collaboration Platform built using **Spring Boot**, **Spring Security**, **JWT Authentication**, and **MySQL**. The application helps organizations manage employees, departments, attendance, leave requests, payroll, and workflow efficiently through REST APIs.

---

## 📌 Project Overview

This project provides a centralized system for managing enterprise operations, including:

- Employee Management
- Department Management
- Leave Management
- Attendance Tracking
- Payroll Management
- User Authentication & Authorization
- Dashboard Analytics
- Email Notifications
- Profile Image Uploads

---

## ✨ Features

### 🔐 Authentication & Security
- JWT Authentication
- Spring Security
- Password Encryption
- Role-Based Access Control

### 👨‍💼 Employee Management
- Add Employee
- Update Employee
- Delete Employee
- View Employee Details

### 🏢 Department Management
- Create Department
- Update Department
- Delete Department
- View Departments

### 📅 Leave Management
- Apply Leave
- Approve/Reject Leave
- View Leave History

### ✅ Attendance Management
- Employee Check-In
- Employee Check-Out
- Attendance History
- Daily Attendance Tracking
- Email Notification after Check-In/Check-Out

### 💰 Payroll Management
- Generate Payroll
- Salary Calculation
- Bonus & Deductions
- Net Salary Calculation
- Payroll Email Notification

### 📊 Dashboard
- Total Employees
- Total Departments
- Employees Present Today
- Employees on Leave
- Total Payroll Records

### ☁️ File Upload
- AWS S3 Profile Image Upload

### 📖 API Documentation
- Swagger UI Integration

---

## 🛠 Tech Stack

- Java 21
- Spring Boot 3
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Swagger / OpenAPI
- AWS S3
- Java Mail Sender

---

## 📂 Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── config
 ├── jwt
 ├── exception
 └── util
```

---

## 🔑 Authentication

The application uses JWT Authentication.

Public APIs

- Register User
- Login

Protected APIs

- Employee APIs
- Department APIs
- Leave APIs
- Attendance APIs
- Payroll APIs
- Dashboard APIs

---

## 📬 Email Notifications

The system automatically sends email notifications for:

- Attendance Check-In
- Attendance Check-Out
- Payroll Generation

---

## 📊 Database

Database Used:

MySQL

Main Tables

- Users
- Roles
- Departments
- Employees
- Leave
- Attendance
- Payroll

---

## 📖 Swagger API

After running the application:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🚀 Future Enhancements

- Frontend Integration (React/Angular)
- Docker Deployment
- Kubernetes Deployment
- Redis Caching
- Microservices Architecture
- Audit Logs
- Notification Service

---

## 👨‍💻 Developed By

**Ranga Swamy**

B.Tech Computer Science Engineering

Java Full Stack Developer

---

## ⭐ If you like this project

Give this repository a ⭐ on GitHub.