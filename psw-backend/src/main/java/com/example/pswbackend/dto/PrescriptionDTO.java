package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.domain.Prescription;

public class PrescriptionDTO {
    private Long id;
    private String patient;
    private String drug;
    private String doctor;

    public PrescriptionDTO(Long id, String patient, String drug, String doctor) {
        this.id = id;
        this.patient = patient;
        this.drug = drug;
        this.doctor = doctor;
    }

    public PrescriptionDTO() {
    }

    public PrescriptionDTO(Prescription prescription) {
        this.id = prescription.getId();
        this.drug = prescription.getDrug().getName();

        Patient patient = prescription.getExaminationReport().getAppointment().getPatient();
        Doctor doctor = prescription.getExaminationReport().getDoctor();
        this.patient = patient.getFirstName() + " " + patient.getLastName();
        this.doctor = doctor.getFirstName() + " " + doctor.getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
