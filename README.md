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

- **Java version:** 17 -21
- **Framework:** Spring Boot3, Spring6
- **Database:** PostgreSql
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


![image](https://github.com/amanrashm/SunBase/assets/104130538/c61dd7d2-d0b6-49c8-a961-1ede8a70ff3e)

![image](https://github.com/amanrashm/SunBase/assets/104130538/2f4425af-1436-45cd-be0d-ab5c4fea03d6)

![image](https://github.com/amanrashm/SunBase/assets/104130538/73ed1e5c-a83b-4bbf-8221-7006268a6eb8)

![image](https://github.com/amanrashm/SunBase/assets/104130538/577d6fdb-4df0-4e32-a6b7-29d7a0d944dc)

![image](https://github.com/amanrashm/SunBase/assets/104130538/5ddcf9c9-3ed8-4104-8be2-f9a31092e168)

![image](https://github.com/amanrashm/SunBase/assets/104130538/a19f0801-ad11-4bfd-8951-059d2eeb54d6)

![image](https://github.com/amanrashm/SunBase/assets/104130538/5ba7378b-7c8d-451f-8bf8-7d75820e6c5b)

![image](https://github.com/amanrashm/SunBase/assets/104130538/19656e98-10a0-4d67-b43d-bdd44533acd4)

![image](https://github.com/amanrashm/SunBase/assets/104130538/73696ed3-a59b-473d-b4de-f52ff75ed35b)
