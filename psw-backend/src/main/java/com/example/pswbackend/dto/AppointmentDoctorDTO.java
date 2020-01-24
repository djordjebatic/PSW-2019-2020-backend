package com.example.pswbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class AppointmentDoctorDTO {

    private long patient;
    private String doctor;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private String startDateTime;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private String endDateTime;
    private String type;

    public AppointmentDoctorDTO(){

    }

    public long getPatient() {
        return patient;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public String getType() {
        return type;
    }

    public String getDoctor() { return doctor; }
}
