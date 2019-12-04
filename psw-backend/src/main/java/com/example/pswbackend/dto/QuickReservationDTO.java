package com.example.pswbackend.dto;

import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Ordination;

public class QuickReservationDTO {

    //private ClinicAdmin clinicAdmin; //TODO dodati *koji* admin pravi brzu rezervaciju
    private Long doctor;
    private Long ordination;
    private String date;
    private String time;
    private float price;
    private int duration; // u minutima
    private String type; //  0 = EXAMINATION, 1 = OPERATION


    public long getDoctor() { return doctor; }

    public long getOrdination() { return ordination; }

    public String getDate() { return date; }

    public String getTime() { return time; }

    public float getPrice() { return price; }

    public int getDuration() { return duration; }

    public String getType() { return type; }
}
