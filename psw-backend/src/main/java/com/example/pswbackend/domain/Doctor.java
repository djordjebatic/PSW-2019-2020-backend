package com.example.pswbackend.domain;

import com.example.pswbackend.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="DOCTOR")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Doctor extends Account {

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Clinic clinic;

    @JsonIgnore
    @ManyToMany(mappedBy = "doctors", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @JsonManagedReference
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<AppointmentRequest> appointmentRequests;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AppointmentType specialization;

    @Column
    private int stars;

    @Column
    private int num_votes;


    public Doctor(){
        this.appointmentRequests = new ArrayList();
        this.appointments = new ArrayList();
        this.stars = 10;
        this.num_votes = 0;
        this.userStatus = UserStatus.NEVER_LOGGED_IN;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getNum_votes() {
        return num_votes;
    }

    public void setNum_votes(int num_votes) {
        this.num_votes = num_votes;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public List<AppointmentRequest> getAppointmentRequests() {
        return appointmentRequests;
    }

    public void setAppointmentRequests(List<AppointmentRequest> appointmentRequest) {
        this.appointmentRequests = appointmentRequest;
    }

    public AppointmentType getSpecialization() {
        return specialization;
    }

    public void setSpecialization(AppointmentType specialization) {
        this.specialization = specialization;
    }
}
