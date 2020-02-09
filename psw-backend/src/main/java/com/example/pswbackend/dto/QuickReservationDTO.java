package com.example.pswbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

public class QuickReservationDTO {

    private String clinicAdmin;
    private String doctor;
    private String ordination;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String startDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String endDateTime;
    private Long price;
    private String type; //  0 = EXAMINATION, 1 = OPERATION
    private int discount;

    public QuickReservationDTO(String clinicAdmin, String doctor, String ordination, String startDateTime, String endDateTime, Long price, String type, int discount) {
        this.clinicAdmin = clinicAdmin;
        this.doctor = doctor;
        this.ordination = ordination;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.price = price;
        this.type = type;
        this.discount = discount;
    }

    public QuickReservationDTO() {
    }

    public String getClinicAdmin() { return clinicAdmin; }

    public String getDoctor() { return doctor; }

    public String getOrdination() { return ordination; }

    public String getStartDateTime() { return startDateTime; }

    public String getEndDateTime() { return endDateTime; }

    public Long getPrice() { return price; }

    public String getType() { return type; }

    public int getDiscount() {
        return discount;
    }
}
