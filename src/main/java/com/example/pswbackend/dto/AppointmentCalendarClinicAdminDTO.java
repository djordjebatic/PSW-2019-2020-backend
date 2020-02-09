package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneId;
import java.util.Date;

public class AppointmentCalendarClinicAdminDTO {

    @Autowired
    DoctorService doctorService;

    private Long id;
    private String title;
    private Date start;
    private Date end;
    private String patient;
    private Long patientId;
    private String nurse;

    public AppointmentCalendarClinicAdminDTO(){

    }

    public AppointmentCalendarClinicAdminDTO(Appointment appointment){

        String patient = appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName();
        String nurse = appointment.getNurse().getFirstName() + " " + appointment.getNurse().getLastName();
        this.id = appointment.getId();
        this.title = appointment.getPrice().getAppointmentEnum().toString();
        this.start = Date.from(appointment.getStartDateTime().atZone(ZoneId.systemDefault()).toInstant());
        this.end = Date.from(appointment.getEndDateTime().atZone(ZoneId.systemDefault()).toInstant());
        this.patient = patient;
        this.patientId = appointment.getPatient().getId();
        this.nurse = nurse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

}
