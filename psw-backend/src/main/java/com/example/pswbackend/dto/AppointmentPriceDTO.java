package com.example.pswbackend.dto;

public class AppointmentPriceDTO {

    String appointmentType;
    String appointmentPrice;

    public AppointmentPriceDTO(String appointmentType, String appointmentPrice) {
        this.appointmentType = appointmentType;
        this.appointmentPrice = appointmentPrice;
    }

    public AppointmentPriceDTO() {

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
}
