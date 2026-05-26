# Real-Time Order Processing System

## Project Overview

This project is a backend microservices-based Real-Time Order Processing System built using Java, Spring Boot, MongoDB, Apache Kafka, and Docker.

The system demonstrates Event Driven Architecture (EDA) where multiple services communicate asynchronously using Kafka events instead of direct REST communication.

The project also implements the Saga Pattern for handling distributed transactions and payment failure scenarios.

---

# Architecture Overview

```text
Client
↓
Order Service
↓
Kafka Topic (order-topic)
↓
├── Notification Service
├── Analytics Service
└── Payment Service
        ↓
 payment-success-topic
 OR
 payment-failed-topic
        ↓
 Order Service updates order status
```

---

# Technologies Used

## Backend
- Java 17
- Spring Boot
- Spring Web
- Spring Kafka
- Spring Data MongoDB
- Maven

## Database
- MongoDB

## Messaging
- Apache Kafka
- Zookeeper

## DevOps / Tools
- Docker
- Docker Compose
- Postman
- IntelliJ IDEA

---

# Microservices Implemented

## 1. Order Service

### Responsibilities
- Create orders
- Store orders in MongoDB
- Publish order events to Kafka
- Handle payment failure events
- Update order status

### APIs
- `POST /orders`
- `GET /orders`

### Port
`8080`

---

## 2. Notification Service

### Responsibilities
- Consume order events
- Process notifications
- Log thank-you messages

### Port
`8081`

---

## 3. Analytics Service

### Responsibilities
- Consume order events
- Process analytics data
- Log revenue and product details

### Port
`8082`

---

## 4. Payment Service

### Responsibilities
- Consume order events
- Simulate payment processing
- Publish payment success/failure events

### Port
`8083`

---

# Event Driven Architecture Flow

## Order Creation Flow

1. Client creates order using `POST /orders`
2. Order Service stores order in MongoDB
3. Order Service publishes event to Kafka topic
4. Multiple services consume the same event independently

---

# Saga Pattern Flow

## Payment Success

```text
order.created
→ payment.processed
→ order remains active
```

## Payment Failure

```text
order.created
→ payment.failed
→ order.cancelled
```

This demonstrates distributed transaction handling using Saga Pattern and compensating transactions.

---

# Kafka Topics Used

- `order-topic`
- `payment-success-topic`
- `payment-failed-topic`

---

# Project Structure

## Example Structure (Order Service)

```text
src/main/java/com/pratiksha/orderservice
│
├── controller
├── service
├── repository
├── producer
├── consumer
├── model
├── event
└── config
```

---

# Setup Instructions

## 1. Clone Repository

```bash
git clone <your-github-repo-url>
```

---

## 2. Start MongoDB

Using Docker:

```bash
docker run -d -p 27017:27017 --name mongodb mongo
```

---

## 3. Start Kafka and Zookeeper

Run:

```bash
docker compose up -d
```

---

## 4. Run Microservices

Run services one by one:

- order-service
- notification-service
- analytics-service
- payment-service

---

# API Testing

## Create Order

```http
POST http://localhost:8080/orders
```

### Request Body

```json
{
  "productName": "Laptop",
  "amount": 70000
}
```

---

## Get Orders

```http
GET http://localhost:8080/orders
```

---

# Key Concepts Implemented

- REST APIs
- Layered Architecture
- Dependency Injection
- Microservices
- Event Driven Architecture
- Kafka Producer/Consumer
- Saga Pattern
- Distributed Transactions
- Compensating Transactions
- Asynchronous Communication

---

# Problems Solved During Development

- Kafka configuration issues
- Port conflicts between services
- Consumer group handling
- Event flow debugging
- Docker setup issues

---

# Future Enhancements

- Event Sourcing
- CQRS
- Dead Letter Queue (DLQ)
- Idempotency Handling
- OpenTelemetry Integration
- WebSocket Live Updates
- Frontend Integration
- Authentication & Authorization

---

# Learning Outcomes

This project helped in understanding:

- Java backend development
- Spring Boot microservices
- Kafka-based asynchronous communication
- Real-world Event Driven Architecture
- Saga Pattern implementation
- Distributed systems fundamentals

---