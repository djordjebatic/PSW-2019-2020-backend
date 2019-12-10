package com.example.pswbackend.dto;

import com.example.pswbackend.enums.AppointmentEnum;

public class CreateOrdinationDTO {

    private String name;
    private AppointmentEnum type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type.name();
    }

    public void setType(String type) {
        this.type = AppointmentEnum.valueOf(type);
    }
}
