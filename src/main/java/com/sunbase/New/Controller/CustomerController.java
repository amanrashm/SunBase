package com.sunbase.New.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunbase.New.Entity.AuthRequest;
import com.sunbase.New.Entity.UserInfo;
import com.sunbase.New.Service.JwtService;
import com.sunbase.New.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:63342")
public class CustomerController {

    @Autowired
    private UserInfoService userService;

    @Autowired
    private JwtService jwtService;

    // Welcome endpoint (not secure) ACTIVE
    @GetMapping("/welcome")
    public String welcome() {
        System.out.println("Welcome to the world of Spring Security");
        return "Welcome, this endpoint is not secure";
    }

    // Add a new customer
    @PostMapping("/addCustomer")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String addCustomer(@RequestBody UserInfo customer) {
        return userService.addCustomer(customer) + "Customer"+ customer.getId()+ " added successfully";
    }

    // Get list of customers with pagination, sorting, and searching
    @GetMapping("/getCustomers")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public List<UserInfo> getCustomers(@RequestParam     String firstName, @RequestParam String lastName){
        return userService.getCustomers(firstName, lastName);
    }

    // Get a single customer based on ID
    @GetMapping("/getCustomer/{customerId}")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public UserInfo getCustomerById(@PathVariable Long customerId) {
        UserInfo userInfo= userService.getCustomerById(customerId);
        return userInfo;
    }

    @GetMapping("/getAllCustomers")
    public List<UserInfo> getAllCustomers() {
        return userService.getCustomers();
    }

    // Update a customer
    @PutMapping("/updateCustomer/{customerId}")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> updateCustomer(@PathVariable Long customerId, @RequestBody UserInfo customer) {
        String result = userService.updateCustomer(customerId, customer);
        return ResponseEntity.ok(result + " User with ID: " + customerId + " updated successfully");
    }

    // Delete a customer
    @DeleteMapping("/deleteCustomer/{customerId}")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String deleteCustomer(@PathVariable Long customerId) {
        return userService.deleteCustomer(customerId) + " User with ID: " + customerId + " deleted successfully";
    }

    // User profile (requires ROLE_USER)
    @GetMapping("/userProfile")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    // Admin profile (requires ROLE_ADMIN)
    @GetMapping("/adminProfile")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    // Generate JWT token
    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        if (userService.authenticateUser(authRequest.getUsername(), authRequest.getPassword())) {
            String token = jwtService.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    // Remote data synchronization
    @PostMapping("/syncData")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<UserInfo>> syncDataFromRemote(@RequestBody AuthRequest authRequest) throws JsonProcessingException {
        // Extract login credentials from the request body
        String loginId = authRequest.getUsername();
        String password = authRequest.getPassword();

        // Call the service method to sync data
        List<UserInfo> remoteData = userService.syncDataFromRemote(loginId, password);

        return ResponseEntity.ok(remoteData);
    }
}