package com.example.pswbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    public AppointmentDoctorDTO(long patient, String doctor, String startDateTime, String endDateTime, String type) {
        this.patient = patient;
        this.doctor = doctor;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.type = type;
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

    public void setPatient(long patient) {
        this.patient = patient;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setType(String type) {
        this.type = type;
    }
}
