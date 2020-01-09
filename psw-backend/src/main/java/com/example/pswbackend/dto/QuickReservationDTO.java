package com.example.pswbackend.dto;

import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Ordination;

public class QuickReservationDTO {

    //private ClinicAdmin clinicAdmin; //TODO dodati *koji* admin pravi brzu rezervaciju
    private String doctor;
    private String ordination;
    private String date;
    private String time;
    private double price;
    private int duration; // u minutima
    private String type; //  0 = EXAMINATION, 1 = OPERATION


    public String getDoctor() { return doctor; }

    public String getOrdination() { return ordination; }

    public String getDate() { return date; }

    public String getTime() { return time; }

    public double getPrice() { return price; }

    public int getDuration() { return duration; }

    public String getType() { return type; }
}
