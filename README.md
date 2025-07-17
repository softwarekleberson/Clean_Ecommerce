# Branch `customer` – Customer Domain Management

## 📚 Overview

This branch focuses on implementations related to the **Customer domain** and its main aggregates:

- **Delivery**
- **Charge**
- **Password**

The architecture follows **Domain-Driven Design (DDD)** and **Clean Architecture** principles, ensuring that all business logic is centralized within the domain root (`Customer`) and its aggregates.

## 🛠 Implemented Features

- Updating customer data and their aggregates
- Adding and removing deliveries and charges directly in the domain
- Secure password update with validations
- Domain-specific exception handling (`IllegalDomainException`)
- Data validation via DTOs and business rules
- Unified RESTful controller for managing customer-related resources

