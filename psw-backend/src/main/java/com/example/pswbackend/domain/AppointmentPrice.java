package com.example.pswbackend.domain;

import com.example.pswbackend.enums.AppointmentEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Double price;

    @OneToMany(mappedBy = "price", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AppointmentType appointmentType;

    public AppointmentPrice() {

    }

    public AppointmentPrice(AppointmentEnum appointmentEnum, Double price) {
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

    public Double getPrice() {
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
