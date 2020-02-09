package com.example.pswbackend.dto;

import java.time.LocalDate;

public class FilterClinicsDTO {

    private LocalDate date;
    private String type;

    public LocalDate getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public FilterClinicsDTO(String type, LocalDate date) {
        this.date = date;
        this.type = type;
    }

    public FilterClinicsDTO() {
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }
}
