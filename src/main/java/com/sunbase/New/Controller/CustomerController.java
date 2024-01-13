package com.sunbase.New.Controller;

import com.sunbase.New.Entity.AuthRequest;
import com.sunbase.New.Entity.UserInfo;
import com.sunbase.New.Service.JwtService;
import com.sunbase.New.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private UserInfoService userService;

    @Autowired
    private JwtService jwtService;

    // Welcome endpoint (not secure)
    @GetMapping("/welcome")
    public String welcome() {
        System.out.println("Welcome to the world of Spring Security");
        return "Welcome, this endpoint is not secure";
    }

    // Add a new customer
    @PostMapping("/addCustomer")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String addCustomer(@RequestBody UserInfo customer) {
        return userService.addCustomer(customer);
    }

    // Get list of customers with pagination, sorting, and searching
    @GetMapping("/getCustomers")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<UserInfo> getCustomers(String firstName, String lastName, Pageable pageable){
        return userService.getCustomers(firstName, lastName, pageable);
    }
    // Add a User
    @PostMapping("/addNewUser")
    public String addUser(@RequestBody UserInfo userInfo) {
        return userService.addUser(userInfo);
    }

    // Get a single customer based on ID
    @GetMapping("/getCustomer/{customerId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public UserInfo getCustomerById(@PathVariable Long customerId) {
        return userService.getCustomerById(customerId);
    }

    // Update a customer
    @PutMapping("/updateCustomer/{customerId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String updateCustomer(@PathVariable Long customerId, @RequestBody UserInfo customer) {
        return userService.updateCustomer(customerId, customer);
    }

    // Delete a customer
    @DeleteMapping("/deleteCustomer/{customerId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String deleteCustomer(@PathVariable Long customerId) {
        return userService.deleteCustomer(customerId);
    }

    // User profile (requires ROLE_USER)
    @GetMapping("/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    // Admin profile (requires ROLE_ADMIN)
    @GetMapping("/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    // Generate JWT token
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        if (userService.authenticateUser(authRequest.getUsername(), authRequest.getPassword())) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    // Remote data synchronization
    @GetMapping("/syncData")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<UserInfo> syncDataFromRemote() {
        return userService.syncDataFromRemote();
    }
}