package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Doctor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class AppointmentCalendarDTO {

    private Long id;
    private String title;
    private Date start;
    private Date end;
    private String patient;
    private String ordination;
    private String nurse;
    private String doctors;

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
        this.ordination = appointment.getOrdination().getNumber();
        this.nurse = nurse;
        this.doctors = doctors;
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
}
