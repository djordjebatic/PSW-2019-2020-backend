package com.example.pswbackend.dto;

public class AppointmentPriceFullDTO {

    Long id;
    String appointmentType;
    String appointmentPrice;
    String appointmentEnum;

    public AppointmentPriceFullDTO() {
    }

    public AppointmentPriceFullDTO(Long id, String appointmentType, String appointmentPrice, String appointmentEnum) {
        this.id = id;
        this.appointmentType = appointmentType;
        this.appointmentPrice = appointmentPrice;
        this.appointmentEnum = appointmentEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointmentPrice() {
        return appointmentPrice;
    }

    public void setAppointmentPrice(String appointmentPrice) {
        this.appointmentPrice = appointmentPrice;
    }

    public String getAppointmentEnum() {
        return appointmentEnum;
    }

    public void setAppointmentEnum(String appointmentEnum) {
        this.appointmentEnum = appointmentEnum;
    }
}
