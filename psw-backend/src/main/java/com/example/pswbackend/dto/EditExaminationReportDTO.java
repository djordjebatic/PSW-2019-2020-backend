package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Diagnosis;
import com.example.pswbackend.domain.Drug;
import com.example.pswbackend.domain.ExaminationReport;
import com.example.pswbackend.domain.Prescription;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EditExaminationReportDTO {

    private String patient;
    private String comment;
    private Diagnosis diagnosis;
    private List<Drug> drugs;
    private LocalDateTime timeCreated;
    private LocalDateTime lastEdited;


    EditExaminationReportDTO(){

    }

    public EditExaminationReportDTO(ExaminationReport examinationReport){
        this.patient = examinationReport.getAppointment().getPatient().getFirstName() + " " + examinationReport.getAppointment().getPatient().getLastName();
        this.comment = examinationReport.getComment();
        this.diagnosis = examinationReport.getDiagnosis();
        List<Drug> drugs = new ArrayList<>();
        for (Prescription p : examinationReport.getPrescriptions()){
            drugs.add(p.getDrug());
        }
        this.timeCreated = examinationReport.getTimeCreated();
        this.lastEdited = examinationReport.getLastEdited();
        this.drugs = drugs;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }
}
