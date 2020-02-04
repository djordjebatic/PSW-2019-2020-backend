package com.example.pswbackend.dto;

import com.example.pswbackend.enums.AppointmentEnum;

import java.time.LocalDateTime;

public class AppointmentRequestDTO {

    private Long patientId;
    private Long clinicId;
    private AppointmentEnum appointmentType;
    private String startTime;
    private LocalDateTime startTime1;
    private Long doctorsId;

    public AppointmentRequestDTO() {
    }

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



    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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
