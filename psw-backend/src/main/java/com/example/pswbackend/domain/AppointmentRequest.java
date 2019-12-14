package com.example.pswbackend.domain;

import com.example.pswbackend.enums.AppointmentEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class AppointmentRequest {

    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Enumerated(EnumType.STRING)
    //private AppointmentEnum type;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @OneToOne
    private Doctor doctor;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private ClinicAdmin clinicAdmin;

    //endregion

    //region Contructors
    public AppointmentRequest(){

    }

    public AppointmentRequest(String date, String time, Doctor doctor, ClinicAdmin clinicAdmin){
        this.date = date;
        this.time = time;
        this.doctor = doctor;
        this.clinicAdmin = clinicAdmin;
    }

    //endregion

    //region Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public ClinicAdmin getClinicAdmin() {
        return clinicAdmin;
    }

    public void setClinicAdmin(ClinicAdmin clinicAdmin) {
        this.clinicAdmin = clinicAdmin;
    }

    //endregion
}
