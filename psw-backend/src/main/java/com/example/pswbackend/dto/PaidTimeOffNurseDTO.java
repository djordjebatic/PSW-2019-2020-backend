package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Nurse;
import com.example.pswbackend.enums.PaidTimeOffStatus;
import com.example.pswbackend.enums.PaidTimeOffType;
import java.time.LocalDateTime;

public class PaidTimeOffNurseDTO {

    private Nurse nurse;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private PaidTimeOffType paidTimeOffType;
    private PaidTimeOffStatus paidTimeOffStatus;
    private String comment;

    public PaidTimeOffNurseDTO(){

    }

    public Nurse getNurse() {
        return nurse;
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

    public PaidTimeOffStatus getPaidTimeOffStatus() {
        return paidTimeOffStatus;
    }

    public String getComment() {
        return comment;
    }
}
