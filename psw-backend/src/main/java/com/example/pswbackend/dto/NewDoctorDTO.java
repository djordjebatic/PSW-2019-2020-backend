package com.example.pswbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;


public class NewDoctorDTO {

    private Long id;
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

    public NewDoctorDTO(Long id,String firstName, String lastName, String username, String phoneNumber, String country, String city, String address, Long clinicId, LocalTime workTimeStart, LocalTime workTimeEnd, String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.address = address;
        this.clinicId = clinicId;
        this.workTimeStart = workTimeStart;
        this.workTimeEnd = workTimeEnd;
        this.specialization = specialization;
    }

    public NewDoctorDTO(){

    }

    public Long getId() {
        return id;
    }

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
