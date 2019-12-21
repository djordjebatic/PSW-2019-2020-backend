package com.example.pswbackend.dto;

public class AppointmentDoctorDTO {

    private String patient;
    private String doctor;
    private String date;
    private String time;
    private String type;

    public String getPatient() {
        return patient;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getDoctor() { return doctor; }
}
