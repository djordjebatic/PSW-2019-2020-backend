package com.example.pswbackend.dto;

public class NewDoctorDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String country;
    private String city;
    private String address;
    private Long clinicId;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Long getClinicId() {
        return clinicId;
    }
}
