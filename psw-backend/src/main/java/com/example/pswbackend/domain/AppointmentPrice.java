package com.example.pswbackend.domain;

import com.example.pswbackend.enums.AppointmentEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AppointmentPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // OPERATION or EXAMINATION
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentEnum appointmentEnum;

    @Column(nullable = false, scale = 2)
    private double price;

    @JsonManagedReference
    @OneToMany(mappedBy = "price", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AppointmentType appointmentType;

    public AppointmentPrice() {

    }

    public AppointmentPrice(AppointmentEnum appointmentEnum, double price) {
        this.appointmentEnum = appointmentEnum;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppointmentEnum getAppointmentEnum() {
        return appointmentEnum;
    }

    public void setAppointmentEnum(AppointmentEnum appointmentEnum) {
        this.appointmentEnum = appointmentEnum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }
}
