package com.example.pswbackend.dto;

public class PAScheduleDTO {

    private Long appointmentId;
    private Long patientId;

    public PAScheduleDTO() {
    }

    public PAScheduleDTO(Long appointmentId, Long patientId) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
