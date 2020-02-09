package com.example.pswbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class NewAppointmentDTO {

    private String appReqId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDateTime;
    private String ordinationId;
    private String patientId;
    private String doctorId;
    private String priceId;
    private String type;

    public NewAppointmentDTO() {
    }

    public String getAppReqId() {
        return appReqId;
    }

    public void setAppReqId(String appReqId) {
        this.appReqId = appReqId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getOrdinationId() {
        return ordinationId;
    }

    public void setOrdinationId(String ordinationId) {
        this.ordinationId = ordinationId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
