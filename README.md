# Customer Management System

This is a simple Customer Management System with CRUD (Create, Read, Update, Delete) functionality. The system allows users to manage customer data, including adding new customers, updating their information, and deleting records.

## Technologies Used

- Backend: Java with Spring Boot
- Frontend: HTML, CSS, JavaScript
- Database: MySQL
- Database: PostgreSql
- Authentication: JWT (JSON Web Token)

## Project Structure

The project is divided into two main components: Backend and Frontend.

### Backend

- **Java version:** 17
- **Framework:** Spring Boot
- **Database:** MySQL
- **Authentication:** JWT (JSON Web Token)

### Frontend

- **HTML, CSS, JavaScript**

## How to Run

### Backend

1. Clone the repository:

   ```bash
   git clone (https://github.com/amanrashm/SunBase/tree/master)[https://github.com/amanrashm/SunBase/tree/master]

   Set up MySQL database and configure the application properties (src/main/resources/application.properties).
   
   ./mvnw spring-boot:run The backend will run on http://localhost:8080.

   Create a Customer:

Method: POST
Endpoint: /api/customers
Payload: JSON data for customer
Update a Customer:

Method: PUT
Endpoint: /api/customers/{customerId}
Payload: JSON data for updated customer
Get List of Customers:

Method: GET
Endpoint: /api/customers
Pagination, sorting, and searching parameters supported
Get Single Customer by ID:

Method: GET
Endpoint: /api/customers/{customerId}
Delete a Customer:

Method: DELETE
Endpoint: /api/customers/{customerId}

Added functionalities to make it more real to use in project

