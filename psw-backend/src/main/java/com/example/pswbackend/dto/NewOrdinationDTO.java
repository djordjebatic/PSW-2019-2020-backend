package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Ordination;

import javax.validation.constraints.NotNull;

public class NewOrdinationDTO {

    @NotNull
    private String number;
    @NotNull
    private String type;
    @NotNull
    private Long clinicId;

    public NewOrdinationDTO() {}

    public NewOrdinationDTO(@NotNull Long clinicId, @NotNull String number, @NotNull String type) {
        this.clinicId = clinicId;
        this.number = number;
        this.type = type;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
