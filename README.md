# Java Messaging Tutorials (JDK 21)

This repository contains a collection of springboot tutorials implemented using **Java 21**. Each project demonstrates a different technology or pattern, focused on asynchronous and event-driven architecture or something else just POCs.

## 🔧 Prerequisites

- Java 21 (JDK)
- Maven 3.9.9
- (Optional) Docker (for Kafka & RabbitMQ setup)

## 📁 Projects

### 📌 `asyncApp`
Demonstrates asynchronous processing using Java concurrency and Spring’s `@Async` support.

### 📌 `kafkaApp`
Covers basic Kafka producer-consumer setup using **Spring Boot** and **Apache Kafka**.

### 📌 `MqApp`
Illustrates messaging with **RabbitMQ**  **ActiveMQ** using Spring AMQP and `RabbitTemplate` and JMSTemplate for .

---

## 🚀 Getting Started

To run any module:
```bash
cd <module-name>
mvn spring-boot:run
