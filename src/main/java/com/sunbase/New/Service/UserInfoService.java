package com.sunbase.New.Service;

import com.sunbase.New.Entity.UserInfo;
import com.sunbase.New.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Service class for managing users.
 */
@Service
public class UserInfoService implements UserDetailsService {

    private final UserInfoRepository repository;
    private final PasswordEncoder encoder;
    private int page;
    private int size;
    private String sortBy;
    private String search;

    @Autowired
    public UserInfoService(UserInfoRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = repository.findByName(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }

    // CRUD operations for customers

    public String addCustomer(UserInfo customer) {
        repository.save(customer);
        return "Customer Added Successfully";
    }

    public List<UserInfo> getCustomers(String firstName, String lastName, Pageable pageable) {
        return repository.findByFirstNameContainingOrLastNameContaining(firstName, lastName, pageable);
    }

    public UserInfo getCustomerById(Long id) {
        return repository.findById((long) Math.toIntExact(id)).orElse(null);
    }

    public String updateCustomer(Long customerId, UserInfo customer) {
        if (repository.existsById((long) Math.toIntExact(customerId))) {
            customer.setId(Math.toIntExact(customerId));
            repository.save(customer);
            return "Customer Updated Successfully";
        } else {
            return "Customer not found";
        }
    }

    public String deleteCustomer(Long id) {
        if (repository.existsById((id))) {
            repository.deleteById((id));
            return "Customer Deleted Successfully";
        } else {
            return "Customer not found";
        }
    }

    // Remote data synchronization

    public List<UserInfo> syncDataFromRemote() {
        // Authentication endpoint
        String authApiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

        // Credentials for authentication
        String loginId = "test@sunbasedata.com";
        String password = "Test@123";

        // Create request body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("login_id", loginId);
        requestBody.put("password", password);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create an HTTP entity with the request body and headers
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the authentication request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> authResponse = restTemplate.exchange(authApiUrl, HttpMethod.POST, requestEntity, String.class);

        // Assuming the authentication response contains a token
        String authToken = authResponse.getBody();

        // Fetch customer data using the obtained token
        String customerApiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";

        // Set the Authorization header with the obtained token
        headers.set("Authorization", "Bearer " + authToken);

        // Make the request to fetch customer data
        ResponseEntity<UserInfo[]> customerResponse = restTemplate.exchange(customerApiUrl, HttpMethod.GET, new HttpEntity<>(headers), UserInfo[].class);

        // Save the fetched data to the local database
        List<UserInfo> remoteData = Arrays.asList(Objects.requireNonNull(customerResponse.getBody()));
        repository.saveAll(remoteData);

        return remoteData;
    }

    public boolean authenticateUser(String username, String password) {
        Optional<UserInfo> userDetail = repository.findByName(username);
        return userDetail.filter(userInfo -> encoder.matches(password, userInfo.getPassword())).isPresent();
    }
}