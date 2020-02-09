package com.example.pswbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class DateAndTimeDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime end;

    public DateAndTimeDTO() {
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
