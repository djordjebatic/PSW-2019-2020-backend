package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Nurse;
import com.example.pswbackend.enums.PaidTimeOffStatus;
import com.example.pswbackend.enums.PaidTimeOffType;
import java.time.LocalDateTime;

public class PaidTimeOffNurseDTO {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private PaidTimeOffType paidTimeOffType;
    private String comment;

    public PaidTimeOffNurseDTO(){

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
