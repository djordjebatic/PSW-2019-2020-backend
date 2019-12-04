package com.example.pswbackend.domain;

import com.example.pswbackend.enums.RoleEnum;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="AccountType")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String firstName;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String lastName;

    @Column(columnDefinition = "VARCHAR(11)", nullable = false)
    @Pattern(regexp = "0[0-9]+")
    private String phoneNumber;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String address;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String city;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String country;

    @Column(name = "roles")
    @NonNull
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Role> accountRoles;

    public Account(){
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Role> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(Set<Role> accountRoles) {
        this.accountRoles = accountRoles;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
