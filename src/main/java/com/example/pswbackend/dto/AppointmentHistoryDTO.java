package com.example.pswbackend.dto;

public class AppointmentHistoryDTO {

    private String startTime;
    private String endTime;
    private String doctorFirstName;
    private String doctorLastName;
    private String type;
    private String specialization;
    private String diagnosis;
    private Long id;
    private Long doctorId;
    private Long clinicId;
    private String clinicName;

    public AppointmentHistoryDTO(String startTime, String endTime, String doctorFirstName, String doctorLastName, String type, String specialization, String diagnosis, Long id, Long doctorId, Long clinicId, String clinicName) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.type = type;
        this.specialization = specialization;
        this.diagnosis= diagnosis;
        this.id=id;
        this.doctorId=doctorId;
        this.clinicId= clinicId;
        this.clinicName= clinicName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
}
