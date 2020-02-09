package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Doctor;

import java.time.format.DateTimeFormatter;

public class PredefinedAppointmentDTO {

    private Long id;
    private String type;
    private String startTime;
    private String endTime;
    private String price;
    private String duration;
    private String doctors;
    private String ordination;
    private String discount;

    public PredefinedAppointmentDTO(Appointment appointment){

        StringBuilder stringBuilder = new StringBuilder();
        for (Doctor d : appointment.getDoctors()){
            stringBuilder.append(d.getFirstName() + " " + d.getLastName() + " ");
        }
        String doctors = stringBuilder.toString();

        this.id = appointment.getId();
        this.type = appointment.getPrice().getAppointmentEnum().toString();
        //this.startTime = Date.from(appointment.getStartDateTime().atZone(ZoneId.systemDefault()).toInstant());
        this.startTime=appointment.getStartDateTime().format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy"));
        this.endTime=appointment.getEndDateTime().format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy"));
        this.price=String.valueOf(appointment.getPrice().getPrice());
        this.duration="0";
        this.doctors = doctors;
        this.ordination = appointment.getOrdination().getNumber();
        this.discount=appointment.getDiscount().toString();

    }

    public PredefinedAppointmentDTO(Appointment appointment, Long a){

        StringBuilder stringBuilder = new StringBuilder();
        for (Doctor d : appointment.getDoctors()){
            stringBuilder.append(d.getFirstName() + " " + d.getLastName() + " ");
        }
        String doctors = stringBuilder.toString();

        this.id = appointment.getId();
        this.type = appointment.getPrice().getAppointmentEnum().toString();
        this.startTime=appointment.getStartDateTime().format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy"));
        this.endTime=appointment.getEndDateTime().format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy"));
        this.price=String.valueOf(appointment.getPrice().getPrice());
        this.doctors = doctors;
        this.ordination = appointment.getOrdination().getNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDoctors() {
        return doctors;
    }

    public void setDoctors(String doctors) {
        this.doctors = doctors;
    }

    public String getOrdination() {
        return ordination;
    }

    public void setOrdination(String ordination) {
        this.ordination = ordination;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
