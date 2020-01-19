package com.example.pswbackend.dto;

public class MedicalRecordDTO {

    private long patientId;
    private long doctorId;

    public MedicalRecordDTO(){

    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }
}
