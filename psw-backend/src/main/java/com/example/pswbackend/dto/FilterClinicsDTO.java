package com.example.pswbackend.dto;

import java.time.LocalDate;
import java.util.Date;

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


}
