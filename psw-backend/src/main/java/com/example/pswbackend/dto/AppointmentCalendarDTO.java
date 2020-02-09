package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Doctor;

import java.time.ZoneId;
import java.util.Date;

public class AppointmentCalendarDTO {

    private Long id;
    private String title;
    private Date start;
    private Date end;
    private String patient;
    private Long patientId;
    private String ordination;
    private Long ordinationId;
    private String nurse;
    private String doctors;
    private String examinationReportIssued;

    public AppointmentCalendarDTO(){

    }

    public AppointmentCalendarDTO(Appointment appointment){

        String patient = appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName();
        String nurse = appointment.getNurse().getFirstName() + " " + appointment.getNurse().getLastName();
        StringBuilder stringBuilder = new StringBuilder();
        for (Doctor d : appointment.getDoctors()){
            stringBuilder.append(d.getFirstName() + " " + d.getLastName() + " ");
        }
        String doctors = stringBuilder.toString();

        this.id = appointment.getId(); //Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        this.title = appointment.getPrice().getAppointmentEnum().toString();
        this.start = Date.from(appointment.getStartDateTime().atZone(ZoneId.systemDefault()).toInstant());
        this.end = Date.from(appointment.getEndDateTime().atZone(ZoneId.systemDefault()).toInstant());
        this.patient = patient;
        this.patientId = appointment.getPatient().getId();
        this.ordination = appointment.getOrdination().getNumber();
        this.ordinationId = appointment.getOrdination().getId();
        this.nurse = nurse;
        this.doctors = doctors;
        if (appointment.getExaminationReport() == null){
            this.examinationReportIssued = "not issued";
        }
        else {
            this.examinationReportIssued = "issued";
        }
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

    public String getOrdination() {
        return ordination;
    }

    public void setOrdination(String ordination) {
        this.ordination = ordination;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public String getDoctors() {
        return doctors;
    }

    public void setDoctors(String doctors) {
        this.doctors = doctors;
    }

    public Long getOrdinationId() {
        return ordinationId;
    }

    public void setOrdinationId(Long ordinationId) {
        this.ordinationId = ordinationId;
    }

    public String getExaminationReportIssued() {
        return examinationReportIssued;
    }

    public void setExaminationReportIssued(String examinationReportIssued) {
        this.examinationReportIssued = examinationReportIssued;
    }
}
