package com.example.pswbackend.dto;

public class PatientDTO {


    private String firstName;
    private String lastName;
    private String email;
    private String medicalNumber;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;

    //a sifra? sme je menjati
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMedicalNumber() {
        return medicalNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPassword() {
        return password;
    }
}
