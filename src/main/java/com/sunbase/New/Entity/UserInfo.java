package com.sunbase.New.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "User_name", length = 250, nullable = true)
    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters")
    private String userName;

    @Column(name = "password", length = 250, nullable = true)
    @Size(min = 8, max = 25, message = "Password must be between 8 and 25 characters")
    private String password;

    @Column(name = "First_name", length = 250, nullable = true)
    private String firstName;

    @Column(name = "Last_name", length = 250, nullable = true)
    private String lastName;

    @Column(name = "Full_name", length = 250, nullable = true)
    @Size(min = 6, max = 250, message = "Password must be between 8 and 25 characters")
    private String name = firstName + " " + lastName;

    @Column(name = "street", length = 250, nullable = true)
    private String street;

    @Column(name = "city", length = 250, nullable = true)
    private String address;

    @Column(name = "state", length = 250, nullable = true)
    private String state;

    @Column(name = "zip", length = 250, nullable = true)
    private String zip;

    @Column(name = "email", length = 250, nullable = true)
    private String email;

    @Column(name = "phone", length = 250, nullable = true)
    private String phone;

    @Column(name = "roles", length = 250, nullable = true)
    private String roles;

}