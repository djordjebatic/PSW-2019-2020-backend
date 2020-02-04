package com.example.pswbackend.dto;

public class AppointmentHistoryDTO {

    private String startTime;
    private String endTime;
    private String doctorFirstName;
    private String doctorLastName;
    private String type;
    private String specialization;

    public AppointmentHistoryDTO(String startTime, String endTime, String doctorFirstName, String doctorLastName, String type, String specialization) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.type = type;
        this.specialization = specialization;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
