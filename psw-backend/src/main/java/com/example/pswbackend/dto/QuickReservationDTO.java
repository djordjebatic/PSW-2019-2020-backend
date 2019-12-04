package com.example.pswbackend.dto;

public class QuickReservationDTO {

    private long doctor;
    private long ordination;
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
