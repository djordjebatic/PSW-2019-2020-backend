package com.example.pswbackend.domain;

import com.example.pswbackend.enums.AppointmentEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AppointmentRequest {

    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private AppointmentEnum type;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(nullable = false)
    private LocalDateTime endDateTime;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Doctor doctor;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Clinic clinic;

    private long patientId;

    @Version
    @Column(name = "version_number", columnDefinition = "integer DEFAULT 0", nullable = false)
    private Long version = 0L;

    //endregion

    //region Contructors
    public AppointmentRequest(){

    }

    public AppointmentRequest(LocalDateTime start, LocalDateTime end, Doctor doctor, Clinic clinic, AppointmentEnum type, long patientId){
        this.startDateTime = start;
        this.endDateTime = end;
        this.doctor = doctor;
        this.clinic = clinic;
        this.type = type;
        this.patientId = patientId;
    }

    //endregion

    //region Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public AppointmentEnum getType() {
        return type;
    }

    public void setType(AppointmentEnum type) {
        this.type = type;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    //endregion
}
