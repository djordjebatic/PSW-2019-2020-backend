package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Doctor;

import java.time.ZoneId;
import java.util.Date;

public class PredefinedAppointmentDTO {

    private Long id;
    private String type;
    private Date startTime;
    private Date endTime;
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
        this.startTime = Date.from(appointment.getStartDateTime().atZone(ZoneId.systemDefault()).toInstant());
        this.endTime = Date.from(appointment.getEndDateTime().atZone(ZoneId.systemDefault()).toInstant());
        this.price=appointment.getPrice().getPrice().toString();
        this.duration="0";
        this.doctors = doctors;
        this.ordination = appointment.getOrdination().getNumber();
        this.discount=appointment.getDiscount().toString();

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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
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
