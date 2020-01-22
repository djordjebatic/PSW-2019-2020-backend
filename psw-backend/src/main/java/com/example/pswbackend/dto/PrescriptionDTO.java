package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.domain.Prescription;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class PrescriptionDTO {
    private Long id;
    private long examinationReportId;
    private String diagnosis;
    private String comment;
    private String patient;
    private String drug;
    private String doctor;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime issued;

    public PrescriptionDTO() {
    }

    public PrescriptionDTO(Prescription prescription) {
        this.id = prescription.getId();
        this.drug = prescription.getDrug().getName();
        this.examinationReportId = prescription.getExaminationReport().getId();
        this.diagnosis = prescription.getExaminationReport().getDiagnosis().getName();
        this.comment = prescription.getExaminationReport().getComment();
        Patient patient = prescription.getExaminationReport().getAppointment().getPatient();
        Doctor doctor = prescription.getExaminationReport().getDoctor();
        this.patient = patient.getFirstName() + " " + patient.getLastName();
        this.doctor = doctor.getFirstName() + " " + doctor.getLastName();
        this.issued = prescription.getExaminationReport().getTimeCreated();
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

    public LocalDateTime getIssued() {
        return issued;
    }

    public long getExaminationReportId() {
        return examinationReportId;
    }
    
    public String getDiagnosis() {
        return diagnosis;
    }

    public String getComment() {
        return comment;
    }
}
