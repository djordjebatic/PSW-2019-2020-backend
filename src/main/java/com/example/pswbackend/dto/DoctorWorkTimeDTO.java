package com.example.pswbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public class DoctorWorkTimeDTO {

    @JsonFormat(pattern = "HH:mm")
    LocalTime start;
    @JsonFormat(pattern = "HH:mm")
    LocalTime end;

    public DoctorWorkTimeDTO() {
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }
}
