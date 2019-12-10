package com.example.pswbackend.dto;

import com.example.pswbackend.enums.AppointmentEnum;

import java.time.LocalDateTime;

public class OrdinationDTO {

    private Long id;
    private String name;
    private AppointmentEnum type;
    private LocalDateTime available;

    public OrdinationDTO(String name, AppointmentEnum type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppointmentEnum getType() {
        return type;
    }

    public void setType(AppointmentEnum type) {
        this.type = type;
    }

    public LocalDateTime getAvailable() {
        return available;
    }

    public void setAvailable(LocalDateTime available) {
        this.available = available;
    }
}
