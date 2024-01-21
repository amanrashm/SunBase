package com.sunbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SunbaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunbaseApplication.class, args);
	}

}
/*### ADD CUSTOMER
POST http://localhost:8080/api/customers/addCustomer
Content-Type: application/json

{
  "userName": "User205689",
  "password": "Password0",
  "firstName": "First0",
  "lastName": "Last0",
  "name": "First0 Last0",
  "street": "Street0",
  "address": "City0",
  "state": "State0",
  "zip": "Zip0",
  "email": "email100@example.com",
  "phone": "123-456-7890",
  "roles": "ROLE_USER"
}
### WELCOME
GET http://localhost:8080/api/customers/welcome
### GET CUSTOMER WITH FIRSTNAME AND LASTNAME
GET http://localhost:8080/api/customers/getCustomers?firstName=First0&lastName=Last0
### GET CUSTOMER BY ID
GET http://localhost:8080/api/customers/getCustomer/1
### GET CUSTOMER BY NAME
GET http://localhost:8080/api/customers/userProfile
### GET ADMIN BY NAME
GET http://localhost:8080/api/customers/adminProfile
### UPDATE CUSTOMER BY ID
PUT http://localhost:8080/api/customers/updateCustomer/2
Content-Type: application/json
{
  "userName": "User0",
  "password": "Password0",
  "firstName": "First0",
  "lastName": "Last0",
  "name": "First0 Last0",
  "street": "Street0",
  "address": "City0",
  "state": "State0",
  "zip": "Zip0",
  "email": "email0@example.com",
  "phone": "123-456-7890"
}
### DELETE CUSTOMER BY ID
DELETE http://localhost:8080/api/customers/deleteCustomer/2
//NON ACTIVE
### GENERATE TOKEN
POST http://localhost:8080/api/customers/generateToken
// NON ACTIVE BAD REQUEST
### SYNC DATA FROM EXTERNAL API
GET http://localhost:8080/api/customers/syncData
Content-Type: application/json
{
    "loginId": "test@sunbasedata.com",
    "password": "Test@123"
} */