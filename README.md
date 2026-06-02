# Microservice Architecture with Spring Cloud Gateway

This repository contains a production-ready microservice architecture built with **Spring Boot** and **Spring Cloud Gateway**. The architecture features an API Gateway that routes incoming client requests to two distinct backend microservices based on URL paths, alongside custom global logging filters and full Docker containerization.

---

## 🎯 Architecture Overview

The system consists of three main components:
1. **API Gateway (Spring Cloud Gateway):** Acts as the single entry point. It manages routing, performs path-based filtering, and logs request details (HTTP Method, Path, and Headers).
2. **Profile Service:** A backend microservice managing user profiles with full CRUD capabilities.
3. **Feedback Service:** A backend microservice designed for collecting, storing, and listing user feedback messages.

### Routing Logic
* `/profiles/**` ➡️ Routes dynamically to **Profile Service**
* `/feedback/**` ➡️ Routes dynamically to **Feedback Service**

---

## ⚙️ Tech Stack

* **Backend Framework:** Java 17, Spring Boot 3.x
* **Microservices Orchestration:** Spring Cloud Gateway
* **Containerization & Deployment:** Docker, Docker Compose
* **Documentation:** OpenAPI / Swagger UI
* **Build Tool:** Maven (with Maven Wrapper included)

---

## ✅ Implemented Features

### 1. API Gateway & Logging Filters
* **Path-Based Routing:** Configured route predicates to seamlessly handle multi-service routing.
* **Custom Global Logging Filter:** Intercepts every incoming request to log the HTTP method, targeted path, and request headers at the gateway level for improved system monitoring.

### 2. Profile Service (CRUD APIs)
Provides robust RESTful endpoints for managing user profiles containing fields such as `name`, `email`, and `bio`.
* `POST /profiles` - Create a new user profile
* `GET /profiles/{id}` - Retrieve a profile by ID
* `PUT /profiles/{id}` - Update an existing profile
* `DELETE /profiles/{id}` - Delete a profile

### 3. Feedback Service (REST APIs)
Handles submissions and analytics of client feedback messages.
* `POST /feedback` - Submit a new feedback entry
* `GET /feedback` - Retrieve a comprehensive list of all feedback entries

---

## 🛠️ Project Structure

```text
├── api-gateway/          # Spring Cloud Gateway service & custom logging filters
├── profile-service/      # Profile Management microservice (CRUD)
├── feedback-service/     # Feedback Collection microservice
├── docker-compose.yml    # Orchestration file to spin up the entire system
└── README.md             # Project documentation
🚀 How to Run the Project
Prerequisites
Make sure you have the following installed on your machine:

Java 17 or higher

Docker & Docker Compose

Step 1: Clone the Repository
Bash
git clone [https://github.com/your-username/your-repo-name.git](https://github.com/your-username/your-repo-name.git)
cd your-repo-name
Step 2: Build the Applications
Build all the services using the included Maven Wrapper:

Bash
# On Linux/macOS:
./mvnw clean package -DskipTests

# On Windows:
mvnw.cmd clean package -DskipTests
Step 3: Spin Up Containers via Docker Compose
Run the following command to build the Docker images and start the API Gateway along with the backend services simultaneously:

Bash
docker-compose up --build
Once the containers are healthy, the API Gateway will start listening for traffic on port 8080 (or your configured gateway port).

📝 API Documentation (Bonus Feature)
Each backend service is documented with Swagger/OpenAPI. Once the application is up and running, you can explore and test the APIs directly via the browser:

Profile Service Documentation: http://localhost:8081/swagger-ui.html

Feedback Service Documentation: http://localhost:8082/swagger-ui.html
