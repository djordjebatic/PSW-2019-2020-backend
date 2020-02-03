package com.example.pswbackend.dto;

import javax.persistence.Column;

public class MedicalRecordDTO {

    private long patientId;
    private Integer height;
    private Integer weight;
    private String bloodType;
    private String allergies;

    public MedicalRecordDTO(){

    }

    public MedicalRecordDTO(long patientId, Integer height, Integer weight, String bloodType, String allergies) {
        this.patientId = patientId;
        this.height = height;
        this.weight = weight;
        this.bloodType = bloodType;
        this.allergies = allergies;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
}
