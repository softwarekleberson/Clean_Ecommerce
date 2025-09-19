# 📚 Bookstore E-commerce

Project developed for the **LES - 2nd Semester of 2025** course, focused on implementing a complete e-commerce system specialized in books.

---

## 📖 Overview

The **Bookstore E-commerce** system aims to provide a robust platform for buying and selling books online.  
It includes features for registration, customer management, sales, inventory control, sales analysis, and personalized recommendations using **Generative AI**.

---

## 🛠 Main Features

### 🔹 Book Management
- Register, update, activate, and deactivate books.  
- Automatic deactivation for out-of-stock or low-demand books.  
- Book search with multiple filters.  

### 🔹 Customer Management
- Register and update customer data.  
- Multiple delivery addresses and credit cards per customer.  
- Isolated password change option.  
- Transaction history available per customer.  

### 🔹 Sales Management
- Shopping cart with item addition, update, and removal.  
- Delivery address selection (existing or new).  
- Payment with credit card, vouchers, or combined methods.  
- Purchase tracking with statuses (Processing, In Transit, Delivered).  
- Exchange management with voucher generation.  

### 🔹 Inventory Control
- Book stock entry and removal.  
- Re-entry of returned items.  
- Automatic sales price calculation.  

### 🔹 Analytics & Reports
- Sales history comparison by period and category.  
- Line chart visualization for sales reports.  

### 🔹 Personalized Recommendations
- **Generative AI** recommendations based on customer history and preferences.  
- Interactive chatbot for book search and customer support.  

---

## ⚙️ Non-Functional Requirements
- ⏱ Query response time of up to **1 second**.  
- 🔒 Strong, confirmed, and encrypted passwords.  
- 🗂 Logging for all write operations.  
- 📊 Sales history reports in line charts.  
- 🤖 Integration with generative AI for recommendations.  

---

## 📜 Business Rules (Summary)
- Book registration must include author, publisher, ISBN, year, edition, dimensions, and pricing group.  
- Every customer must have at least one billing and one shipping address.  
- Stock must be validated in all operations (cart, purchase, exchange).  
- Purchases can be split across multiple credit cards (minimum $10 per card) and/or vouchers.  
- Cart items are temporarily locked and automatically removed after expiration.  
- Automatic generation of exchange vouchers for returns or overpayments.  

---

## 🚀 Future Technologies
- Spring Boot / Spring Cloud  
- React for frontend  
- MySQL as the database  
- Integration with generative AI services  
