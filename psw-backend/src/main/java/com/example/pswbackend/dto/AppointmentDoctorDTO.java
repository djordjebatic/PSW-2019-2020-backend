package com.example.pswbackend.dto;

public class AppointmentDoctorDTO {

    private String patient;
    private String doctor;
    private String date;
    private String time;

    public String getPatient() {
        return patient;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDoctor() {
        return doctor;
    }
}
