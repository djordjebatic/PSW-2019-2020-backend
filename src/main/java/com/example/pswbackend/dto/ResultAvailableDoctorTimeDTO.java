package com.example.pswbackend.dto;

public class ResultAvailableDoctorTimeDTO {

    private Long timeId;
    private String time;

    public ResultAvailableDoctorTimeDTO() {
    }

    public ResultAvailableDoctorTimeDTO(Long timeId, String time) {
        this.timeId = timeId;
        this.time = time;
    }

    public Long getTimeId() {
        return timeId;
    }

    public void setTimeId(Long timeId) {
        this.timeId = timeId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
