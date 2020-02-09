package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.enums.AppointmentEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import com.example.pswbackend.enums.AppointmentEnum;

import java.time.LocalDateTime;

public class AppointmentRequestDTO {

    private Long id;
    private String typeEnum;
    private String typeSpec;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDateTime;
    private String doctorFN;
    private String doctorLN;
    private Long doctorId;
    private String patientFN;
    private String patientLN;
    private Long patientId; //zajednicko
    private Long priceId;
    private double price;

    //Sara
    private Long clinicId;
    private AppointmentEnum appointmentType;
    private String startTime;
    private LocalDateTime startTime1;
    private Long doctorsId;
    //


    public AppointmentRequestDTO() {
    }

    public AppointmentRequestDTO(Long id, String typeEnum, LocalDateTime startDateTime, LocalDateTime endDateTime, String doctorFN, String doctorLN, Long doctorId) {
        this.id = id;
        this.typeEnum = typeEnum;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.doctorFN = doctorFN;
        this.doctorLN = doctorLN;
        this.doctorId = doctorId;
    }

    //Sara
    public AppointmentRequestDTO(Long patientId, Long clinicId, AppointmentEnum appointmentType, String startTime, Long doctorsId) {
        this.patientId = patientId;
        this.clinicId = clinicId;
        this.appointmentType = appointmentType;
        this.startTime = startTime;
        this.doctorsId = doctorsId;
    }

    public AppointmentRequestDTO(Long patientId, Long clinicId, AppointmentEnum appointmentType, LocalDateTime startTime, Long doctorsId) {
        this.patientId = patientId;
        this.clinicId = clinicId;
        this.appointmentType = appointmentType;
        this.startTime1 = startTime;
        this.doctorsId = doctorsId;
    }
    //

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

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public AppointmentEnum getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentEnum appointmentType) {
        this.appointmentType = appointmentType;
    }

    public LocalDateTime getStartTime1() {
        return startTime1;
    }

    public void setStartTime1(LocalDateTime startTime) {
        this.startTime1 = startTime;
    }

    public Long getDoctorsId() {
        return doctorsId;
    }

    public void setDoctorsId(Long doctorsId) {
        this.doctorsId = doctorsId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTimeS(String startTime) {
        this.startTime = startTime;

    }

}
