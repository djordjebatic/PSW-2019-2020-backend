package com.example.pswbackend.dto;

public class AppointmentDoctorDTO {

    private Long patientId;
    private String date;
    private String time;

    public Long getPatientId() {
        return patientId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
