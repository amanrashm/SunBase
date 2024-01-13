package com.sunbase.New.Entity;
import jakarta.persistence.*;
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


    @Column(name = "User_name", length = 25, nullable = false)
    private String userName;

    @Column(name = "password", length = 25, nullable = false)
    private String password;

    @Column(name = "First_name", length = 25, nullable = false)
    private String firstName;

    @Column(name = "Last_name", length = 25, nullable = false)
    private String lastName;

    @Column(name = "Full_name", length = 50, nullable = false)
    private String name = firstName + " " + lastName;

    @Column(name = "street", length = 25, nullable = false)
    private String street;

    @Column(name = "city", length = 25, nullable = false)
    private String address;

    @Column(name = "state", length = 25, nullable = false)
    private String state;

    @Column(name = "zip", length = 25, nullable = false)
    private String zip;

    @Column(name = "email", length = 25, nullable = false)
    private String email;

    @Column(name = "phone", length = 25, nullable = false)
    private String phone;

    @Column(name = "roles", length = 25, nullable = false)
    private String roles;

}
