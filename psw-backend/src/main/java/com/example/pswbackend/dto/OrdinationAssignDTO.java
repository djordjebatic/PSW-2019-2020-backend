package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Ordination;
import javax.validation.constraints.NotNull;

public class OrdinationAssignDTO {

    @NotNull
    private Long id;
    @NotNull
    private String number;
    @NotNull
    private String type;

    public OrdinationAssignDTO() {}

    public OrdinationAssignDTO(Ordination ordination) {
        this.id = ordination.getId();
        this.number = ordination.getNumber();
        this.type = ordination.getType().toString();
    }

    public OrdinationAssignDTO(@NotNull Long id, @NotNull String number, @NotNull String type) {
        this.id = id;
        this.number = number;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
