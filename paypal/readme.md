# 💳 PayPal Integration with Spring Boot

A simple project demonstrating PayPal integration using **Spring Boot** and **Thymeleaf**. Designed with extensibility in mind for multiple payment methods and future upgrades.

---

## 🚀 Features

- 🔁 Basic PayPal Checkout Flow
- 🧩 Spring Boot + Thymeleaf Integration
- 📄 RESTful architecture
- 🛠️ Modular structure for adding other payment gateways

---

## 📌 TODOs & Planned Enhancements

> This project is a WIP (Work in Progress) and will evolve with better architecture and modern practices:

- [ ] ❌ **Stop using Feign Client for PayPal**  
  Replace with direct HTTP clients as per PayPal's [2020 API best practices](https://developer.paypal.com/docs/api/overview/).

- [ ] 🔁 **Use Protocol Buffers (protobuf)**  
  Optimize request/response serialization for high-performance communication.

- [ ] 🚨 **Improve Exception Handling**  
  Add global exception handler (`@ControllerAdvice`) and more granular error messages.

- [ ] 💱 **Add Multi-Currency Support**  
  Handle payments in different currencies with proper formatting and exchange rate integration.

- [ ] 🎟️ **Coupon Code System**  
  Apply discounts during checkout via unique coupon codes.

- [ ] 💳 **Add More Payment Methods**  
  - Stripe Integration
  - Razorpay (merchant + UPI)
  - Direct Card Payments via PayPal SDK

---

## 🧰 Tech Stack

| Layer        | Technology             |
|--------------|------------------------|
| Backend      | Spring Boot 3.x        |
| Frontend     | Thymeleaf              |
| Payments     | PayPal REST API        |
| Build Tool   | Maven                  |
| View Engine  | HTML + Thymeleaf       |

---

## 📦 Setup

1. **Clone the Repo**

   ```bash
   git clone https://github.com/ashwani-bhadani/spboot-tutorials.git
   cd paypal
