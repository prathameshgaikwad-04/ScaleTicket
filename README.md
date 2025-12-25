# ScaleTicket - High-Concurrency Booking System

A Spring Boot microservice designed to handle 1,000+ simultaneous booking requests using a distributed architecture.

## 🚀 Tech Stack
- **Backend:** Spring Boot, Hibernate
- **Database:** PostgreSQL (Persistence)
- **Caching/Locking:** Redis (Distributed Locking)
- **Messaging:** Apache Kafka (Event-driven updates)
- **Containerization:** Docker & Docker Compose

## 🛠 How to Run
1. Run `docker-compose up -d` to start the infrastructure.
2. Run the Spring Boot application.
3. Use the provided load test script to simulate 1,000 users.

## 📈 Load Test Results
During testing, the system successfully handled 1,000 concurrent requests, preventing double-bookings through Redis distributed locks and Optimistic Locking in Postgres.
