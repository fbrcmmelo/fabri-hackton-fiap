# Microservices System for SUS (Brazil's Public Healthcare System)

## 🚀 Overview

This project is a robust, scalable **microservices platform** developed to modernize the Identity and Service Management processes for the Brazilian Public Healthcare System (SUS).

The primary goal was to replace bureaucratic, monolithic identity management with a resilient and secure distributed system, built to handle the complex, real-world requirement where a single user may hold multiple roles simultaneously (e.g., both **Patient** and **Healthcare Professional**).

## 💡 Architecture & Technical Stack

The solution is powered by a set of decoupled microservices, containerized for portability and orchestration.

| Component | Technology | Rationale |
| :--- | :--- | :--- |
| **Core Development** | **Java 17 / Spring Boot** | Used as the high-performance, enterprise-grade backbone for all service development. |
| **Service Discovery** | **Eureka Server** | Enables dynamic service registration and discovery, essential for distributed systems. |
| **Edge Routing** | **API Gateway** | Single, unified entry point for clients, managing intelligent request routing and preliminary security checks. |
| **Security & Auth** | **Spring Security & JWT** | Implements secure, **stateless** authentication and granular authorization using JSON Web Tokens. |
| **Asynchronous Comms** | **RabbitMQ** | Decouples services for event processing, background tasks, and reliable messaging. |
| **Data Persistence** | **Polyglot Persistence** | Combines **PostgreSQL** (for structured, transactional data) and **MongoDB** (for non-relational data like logs or dynamic configuration). |
| **Containerization** | **Docker** | Used to package all microservices, ensuring consistency and simplified deployment across environments. |
| **Monitoring** | **Observability** | Integrated tooling for continuous health monitoring, utilizing structured logging and metrics to ensure operational readiness. |

## 🛠️ Key Functionalities & Achievements

This system addresses complex domain logic and showcases strong architectural skills:

* **Complex Identity Management (N:N):** Successfully implemented a **Many-to-Many (N:N)** data model between Users and Roles. This critical feature allows a single individual to be registered simultaneously as a **Patient** and a **Healthcare Professional**, maintaining data integrity within the complex SUS environment.

* **Scalable API Design:** Developed a comprehensive **API RESTful** using **Clean Architecture** principles, ensuring separation of concerns, testability, and long-term maintainability.

* **Distributed Persistence:** Successfully managed data across both relational (**PostgreSQL**) and non-relational (**MongoDB**) databases to maximize performance and flexibility based on data type.

* **System Deployment:** The entire application stack is fully containerized, demonstrating proficiency in preparing complex systems for scalable production environments.
