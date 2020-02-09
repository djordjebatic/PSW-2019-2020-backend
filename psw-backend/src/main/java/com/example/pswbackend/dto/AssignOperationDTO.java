package com.example.pswbackend.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AssignOperationDTO {

    private Long id;
    @NotNull
    private Long appointmentId;
    @NotNull
    private Long ordinationId;
    @NotNull
    private List<Long> doctorIds;

    public AssignOperationDTO(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public long getOrdinationId() {
        return ordinationId;
    }

    public void setOrdinationId(long ordinationId) {
        this.ordinationId = ordinationId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setOrdinationId(Long ordinationId) {
        this.ordinationId = ordinationId;
    }

    public List<Long> getDoctorIds() {
        return doctorIds;
    }

    public void setDoctorIds(List<Long> doctorIds) {
        this.doctorIds = doctorIds;
    }
}
