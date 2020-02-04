package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.enums.AppointmentEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

public class AppointmentRequestDTO {

    private Long id;
    private String typeEnum;
    private String typeSpec;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime startDateTime;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime endDateTime;
    private String doctorFN;
    private String doctorLN;
    private Long doctorId;
    private String patientFN;
    private String patientLN;
    private Long patientId;
    private Long priceId;
    private double price;

    public AppointmentRequestDTO(Long id, String typeEnum, LocalDateTime startDateTime, LocalDateTime endDateTime, String doctorFN, String doctorLN, Long doctorId) {
        this.id = id;
        this.typeEnum = typeEnum;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.doctorFN = doctorFN;
        this.doctorLN = doctorLN;
        this.doctorId = doctorId;
    }

    public Long getId() {
        return id;
    }

    public String getTypeEnum() {
        return typeEnum;
    }

    public String getTypeSpec() {
        return typeSpec;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public String getDoctorFN() {
        return doctorFN;
    }

    public String getDoctorLN() {
        return doctorLN;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public String getPatientFN() {
        return patientFN;
    }

    public String getPatientLN() {
        return patientLN;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientFN(String patientFN) {
        this.patientFN = patientFN;
    }

    public void setPatientLN(String patientLN) {
        this.patientLN = patientLN;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public double getPrice() {
        return price;
    }

    public void setTypeSpec(String typeSpec) {
        this.typeSpec = typeSpec;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
