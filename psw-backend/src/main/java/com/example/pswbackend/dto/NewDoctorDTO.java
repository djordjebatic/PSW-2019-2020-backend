package com.example.pswbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;


public class NewDoctorDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String country;
    private String city;
    private String address;
    private Long clinicId;
    @JsonFormat(pattern="HH:mm")
    private LocalTime workTimeStart;
    @JsonFormat(pattern="HH:mm")
    private LocalTime workTimeEnd;
    private String specialization;

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

    public LocalTime getWorkTimeStart() {
        return workTimeStart;
    }

    public LocalTime getWorkTimeEnd() {
        return workTimeEnd;
    }

    public String getSpecialization() {
        return specialization;
    }
}
