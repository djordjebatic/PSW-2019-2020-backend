package com.example.pswbackend.dto;

import java.time.LocalDate;

public class AvailableDoctorTimeDTO {

    private LocalDate date;
    private String doctorsId;

    public AvailableDoctorTimeDTO(LocalDate date, String doctorsId) {
        this.date = date;
        this.doctorsId = doctorsId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDoctorsId() {
        return doctorsId;
    }

    public void setDoctorsId(String doctorsId) {
        this.doctorsId = doctorsId;
    }
}
