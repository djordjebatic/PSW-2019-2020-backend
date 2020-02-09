package com.example.pswbackend.dto;

import java.time.LocalDate;

public class FilterDoctorsDTO {

    private LocalDate date;
    private String type;
    private String clinicsId;

    public FilterDoctorsDTO(LocalDate date, String type, String clinicsId) {
        this.date = date;
        this.type = type;
        this.clinicsId = clinicsId;
    }

    public FilterDoctorsDTO(String type, LocalDate date) {
        this.date = date;
        this.type = type;
    }

    public FilterDoctorsDTO() {
    }

    public LocalDate getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getClinicsId() {
        return clinicsId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setClinicsId(String clinicsId) {
        this.clinicsId = clinicsId;
    }
}
