package com.example.pswbackend.dto;

import com.example.pswbackend.enums.PaidTimeOffType;

import java.time.LocalDateTime;

public class PaidTimeOffDoctorDTO {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private PaidTimeOffType paidTimeOffType;
    private String comment;

    public PaidTimeOffDoctorDTO(){

    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public PaidTimeOffType getPaidTimeOffType() {
        return paidTimeOffType;
    }

    public String getComment() {
        return comment;
    }

}
