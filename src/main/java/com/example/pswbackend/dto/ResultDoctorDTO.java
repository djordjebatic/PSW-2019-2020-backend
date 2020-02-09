package com.example.pswbackend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ResultDoctorDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String rating;
    private List<LocalDateTime> freeTerms;
    private List<String> free;
    private List<ResultAvailableDoctorTimeDTO> dto;

    public ResultDoctorDTO() {
    }

    public ResultDoctorDTO(String id,String firstName, String lastName, String rating, List<LocalDateTime> freeTerms, List<String> free) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.freeTerms = freeTerms;
        this.free= free;

    }

    public ResultDoctorDTO(String id,String firstName, String lastName, String rating, List<LocalDateTime> freeTerms, List<String> free, List<ResultAvailableDoctorTimeDTO> dto) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.freeTerms = freeTerms;
        this.free= free;
        this.dto=dto;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String stars) {
        this.rating = rating;
    }


    public List<LocalDateTime> getFreeTerms() {
        return freeTerms;
    }

    public void setFreeTerms(List<LocalDateTime> freeTerms) {
        this.freeTerms = freeTerms;
    }

    public List<String> getFree() {
        return free;
    }

    public void setFree(List<String> free) {
        this.free = free;
    }

    public List<ResultAvailableDoctorTimeDTO> getDto() {
        return dto;
    }

    public void setDto(List<ResultAvailableDoctorTimeDTO> dto) {
        this.dto = dto;
    }
}
