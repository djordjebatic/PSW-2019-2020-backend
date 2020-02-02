package com.example.pswbackend.dto;

import com.example.pswbackend.enums.PaidTimeOffType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class AbsenceDoctorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime startDateTime;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime endDateTime;
    private PaidTimeOffType paidTimeOffType;
    private String comment;
    private String denialComment;

    public AbsenceDoctorDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDenialComment() {
        return denialComment;
    }

    public void setDenialComment(String denialComment) {
        this.denialComment = denialComment;
    }
}
