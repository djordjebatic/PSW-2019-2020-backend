package com.example.pswbackend.dto;

import java.util.Date;

public class FilterClinicsDTO {

    private String date;
    private String type;

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public FilterClinicsDTO(String type, String date) {
        this.date = date;
        this.type = type;
    }

    public FilterClinicsDTO() { super();
    }


}
