package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Patient;

public class DoctorAppointmentDto {

    private long patientId;
    private String date;
    private String time;

    public long getPatientId() {
        return patientId;
    }

    public String getDate() {
        return date;
    }


    public String getTime() {
        return time;
    }

}
