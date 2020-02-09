package com.example.pswbackend.dto;

public class VoteDTO {

    private String forId;
    private String patientId;
    private String rating;

    public VoteDTO(String forId, String patientId, String rating) {
        this.forId = forId;
        this.patientId = patientId;
        this.rating = rating;
    }

    public VoteDTO() {
    }

    public String getForId() {
        return forId;
    }

    public void setForId(String doctorId) {
        this.forId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
