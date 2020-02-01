package com.example.pswbackend.dto;


public class AppointmentTypeDTO {

    private Long id;
    private String name;
    private Long clinicId;

    public AppointmentTypeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public AppointmentTypeDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClinicId() { return clinicId; }

    public void setClinicId(Long clinicId) { this.clinicId = clinicId;}
}
