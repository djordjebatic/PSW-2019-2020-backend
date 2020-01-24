package com.example.pswbackend.dto;

import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Ordination;
import com.fasterxml.jackson.annotation.JsonFormat;

public class QuickReservationDTO {

    private String clinicAdmin;
    private String doctor;
    private String ordination;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String startDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String endDateTime;
    private double price;
    private String type; //  0 = EXAMINATION, 1 = OPERATION


    public String getClinicAdmin() { return clinicAdmin; }

    public String getDoctor() { return doctor; }

    public String getOrdination() { return ordination; }

    public String getStartDateTime() { return startDateTime; }

    public String getEndDateTime() { return endDateTime; }

    public double getPrice() { return price; }

    public String getType() { return type; }
}
