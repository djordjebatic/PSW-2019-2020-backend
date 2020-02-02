package com.example.pswbackend.domain;

import com.example.pswbackend.enums.PaidTimeOffStatus;
import com.example.pswbackend.enums.PaidTimeOffType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PaidTimeOffDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Doctor doctor;

    @Column
    private String commentt;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(nullable = false)
    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    private PaidTimeOffType paidTimeOffType;

    @Enumerated(EnumType.STRING)
    private PaidTimeOffStatus paidTimeOffStatus;

    public PaidTimeOffDoctor() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doc) {
        this.doctor = doc;
    }

    public String getComment() {
        return commentt;
    }

    public void setComment(String comment) {
        this.commentt = comment;
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

    public PaidTimeOffType getPaidTimeOffType() {
        return paidTimeOffType;
    }

    public void setPaidTimeOffType(PaidTimeOffType paidTimeOffType) {
        this.paidTimeOffType = paidTimeOffType;
    }

    public PaidTimeOffStatus getPaidTimeOffStatus() {
        return paidTimeOffStatus;
    }

    public void setPaidTimeOffStatus(PaidTimeOffStatus paidTimeOffStatus) {
        this.paidTimeOffStatus = paidTimeOffStatus;
    }
}
