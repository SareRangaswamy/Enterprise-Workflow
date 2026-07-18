 # 🚀 Enterprise Workflow Management System (Backend)

A production-ready **Enterprise Workflow Management System Backend** built with **Spring Boot** following RESTful API architecture. The project provides secure authentication, employee and department management, leave management, password reset with OTP, email notifications, and cloud deployment on AWS.

---

# 📖 Overview

This backend application is designed to automate enterprise workflow operations by providing secure REST APIs for managing employees, departments, leave requests, attendance, and authentication.

The application is deployed on **AWS EC2** with **AWS RDS MySQL** and documented using **Swagger OpenAPI**.

---

# ✨ Features

- 🔐 JWT Authentication
- 👤 User Registration & Login
- 🔑 Forgot Password with OTP Verification
- 🏢 Department Management
- 👨‍💼 Employee Management
- 📝 Leave Management
- 📅 Attendance Management
- 📧 Email Notifications
- 🛡 Spring Security
- 📄 Swagger API Documentation
- ✅ Bean Validation
- ⚠ Global Exception Handling
- ☁ AWS EC2 Deployment
- 🗄 AWS RDS MySQL Database

---

# 🛠 Tech Stack

| Technology | Version |
|------------|---------|
| Java | 21 |
| Spring Boot | 3.x |
| Spring Security | Latest |
| Spring Data JPA | Latest |
| Hibernate | Latest |
| JWT | Latest |
| MySQL | 8.x |
| Maven | Latest |
| Swagger OpenAPI | Latest |
| AWS EC2 | Deployment |
| AWS RDS | MySQL Database |
| Git & GitHub | Version Control |

---

# 📂 Project Structure

```text
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── jwt
 ├── config
 ├── exception
 └── resources
```

---

# 🔐 Authentication

The project uses **JWT (JSON Web Token)** for securing REST APIs.

Available authentication APIs:

- Register User
- Login
- Forgot Password (OTP)
- Protected Profile API

---

# 📚 REST APIs

### Authentication

- Register User
- Login
- Forgot Password
- Profile

### Department

- Create Department
- Get All Departments
- Get Department By ID
- Update Department
- Delete Department

### Employee

- Create Employee
- Get Employee
- Update Employee
- Delete Employee

### Leave

- Apply Leave
- Approve Leave
- Reject Leave
- View Leave History

### Attendance

- Mark Attendance
- View Attendance

---

# 📄 API Documentation

Swagger UI

```
http://16.16.216.12:8080/swagger-ui/index.html
```

OpenAPI JSON

```
http://16.16.216.12:8080/v3/api-docs
```

---

# ☁ Deployment

The application is deployed using:

- AWS EC2
- AWS RDS (MySQL)
- Linux Systemd Service
- GitHub

---

# 🔒 Security Features

- JWT Authentication
- Password Encryption (BCrypt)
- Role-Based Authorization
- Protected REST Endpoints
- Input Validation

---

# 🚀 Getting Started

### Clone Repository

```bash
git clone https://github.com/SareRangaswamy/Enterprise-Workflow.git
```

### Navigate

```bash
cd Enterprise-Workflow
```

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

---

# ⚙ Configuration

Create an `application.properties` file with your own configuration for:

- MySQL Database
- Email (SMTP)
- AWS S3 (if used)
- JWT Secret

Do **not** commit sensitive credentials to Git.

---

# 📌 Future Enhancements

- Docker Support
- CI/CD Pipeline
- Redis Caching
- Monitoring & Logging
- Frontend Integration

---

# 👨‍💻 Author

**Ranga Swamy**

Java Backend Developer

GitHub:
https://github.com/SareRangaswamy

---
⭐ If you found this project useful, consider giving it a star.
