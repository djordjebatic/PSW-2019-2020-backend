package com.example.pswbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="AccountType")
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String firstName;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String lastName;

    @Column(columnDefinition = "VARCHAR(11)", unique = true, nullable = false)
    @Pattern(regexp = "0[0-9]+")
    private String phoneNumber;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String address;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String city;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String country;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "authorities")
    @NonNull
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Role> authorities;

    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;


    public Account(){
    }

    public Long getId() {
        return id;
    }

    // ------------------------ zove se username ali vraca email zbog nasledjivanja
    @Override
    public String getUsername() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Timestamp now = new Timestamp(DateTime.now().getMillis());
        this.setLastPasswordResetDate( now );
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

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}