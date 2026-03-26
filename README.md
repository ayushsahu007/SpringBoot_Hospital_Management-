# 🏥 Hospital Management System

A **production-style Spring Boot backend application** designed to manage hospital operations such as patients, doctors, and appointments. This project demonstrates **real-world backend architecture, security, and scalable design principles**.

---

## 🚀 Features

* 👨‍⚕️ Doctor Management (CRUD)
* 🧑 Patient Management
* 📅 Appointment Scheduling System
* 🔐 JWT + OAuth2 Authentication
* 🛡️ Role-Based Authorization (Admin/User)
* 🔄 DTO Mapping (ModelMapper)
* ⚡ Exception Handling (Global)
* 📊 RESTful API Design

---

## 🛠️ Tech Stack

* **Language:** Java (JDK 17)
* **Framework:** Spring Boot
* **Modules:** Spring MVC, Spring Security
* **ORM:** Hibernate (JPA)
* **Database:** MySQL
* **Build Tool:** Maven
* **Utilities:** Lombok, ModelMapper

---

## 🧱 System Architecture

```
                ┌──────────────────────┐
                │      Client (UI)     │
                │  Postman / Frontend  │
                └─────────┬────────────┘
                          │ HTTP Request
                          ▼
                ┌──────────────────────┐
                │     Controller       │
                │  (REST Endpoints)    │
                └─────────┬────────────┘
                          │
                          ▼
                ┌──────────────────────┐
                │       Service        │
                │ Business Logic Layer │
                └─────────┬────────────┘
                          │
                          ▼
                ┌──────────────────────┐
                │     Repository       │
                │   (JPA Interface)    │
                └─────────┬────────────┘
                          │
                          ▼
                ┌──────────────────────┐
                │      Database        │
                │       MySQL          │
                └──────────────────────┘
```

---

## 🔁 Sequence Flow (Appointment Booking)

```
Client → Controller → Service → Repository → Database

1. Client sends POST /appointments
2. Controller receives request
3. DTO is validated & mapped
4. Service layer applies business logic:
   - Check doctor availability
   - Validate patient
5. Repository saves appointment
6. Database stores data
7. Response returned to client
```

---

## 📂 Project Structure

```
HospitalManagementSystem
│── controller        → Handles API requests
│── service           → Business logic
│── repository        → Database interaction
│── entity            → JPA entities (tables)
│── dto               → Data Transfer Objects
│── config            → App configurations
│── security          → JWT & OAuth2 logic
│── exception         → Global exception handling
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/HospitalManagementSystem.git
cd HospitalManagementSystem
```

### 2️⃣ Configure Database

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3️⃣ Run Application

```bash
mvn spring-boot:run
```

App runs at:

```
http://localhost:8080
```

---

## 🔐 Security Architecture

* JWT Token Generation after login
* Request filter validates token
* Role-based access (ADMIN / USER)
* OAuth2 login (optional)

```
Client → Login → JWT Generated → Stored → Sent in Header
Authorization: Bearer <token>
```

---

## 📡 API Endpoints (Sample)

| Method | Endpoint          | Description      |
| ------ | ----------------- | ---------------- |
| POST   | /api/auth/login   | User login       |
| POST   | /api/patients     | Create patient   |
| GET    | /api/patients     | Get all patients |
| POST   | /api/appointments | Book appointment |
| GET    | /api/doctors      | List doctors     |

---

## 🧠 Interview Explanation Points

### 🔹 Why Spring Boot?

* Reduces configuration
* Embedded server (Tomcat)
* Faster development

---

### 🔹 Why DTO?

* Prevents exposing entity directly
* Improves security
* Controls API response structure

---

### 🔹 Why JPA/Hibernate?

* No need to write SQL manually
* Handles object-relational mapping
* Improves productivity

---

### 🔹 Why Layered Architecture?

* Separation of concerns
* Easy testing & maintenance
* Scalable design

---

### 🔹 Why JWT?

* Stateless authentication
* Secure API communication
* No session storage required

---

### 🔹 How Exception Handling Works?

* Global exception handler (@ControllerAdvice)
* Centralized error handling
* Clean API responses

---

## 🔮 Future Enhancements

* 💳 Payment Gateway Integration
* 📧 Email/SMS Notifications
* 📊 Admin Dashboard (React)
* 🐳 Docker Deployment
* ☁️ Microservices Architecture

---

## 👨‍💻 Author

**Ayush Sahu**
Java Developer | Spring Boot | Backend Engineer

---

## ⭐ Contribution

Contributions are welcome! Feel free to fork and improve.

---


