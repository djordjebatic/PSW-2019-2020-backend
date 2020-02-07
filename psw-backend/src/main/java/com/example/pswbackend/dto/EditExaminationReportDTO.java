package com.example.pswbackend.dto;

import com.example.pswbackend.domain.Diagnosis;
import com.example.pswbackend.domain.Drug;
import com.example.pswbackend.domain.ExaminationReport;
import com.example.pswbackend.domain.Prescription;
import com.example.pswbackend.enums.PrescriptionEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EditExaminationReportDTO {

    private String patient;
    private String comment;
    private Diagnosis diagnosis;
    private List<Drug> drugs;
    private List<String> drugString;


    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime timeCreated;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime lastEdited;


    EditExaminationReportDTO(){

    }

    public EditExaminationReportDTO(ExaminationReport examinationReport){
        this.patient = examinationReport.getAppointment().getPatient().getFirstName() + " " + examinationReport.getAppointment().getPatient().getLastName();
        this.comment = examinationReport.getComment();
        this.diagnosis = examinationReport.getDiagnosis();
        List<Drug> drugs = new ArrayList<>();
        List<String> drugString = new ArrayList<>();
        for (Prescription p : examinationReport.getPrescriptions()){
            drugs.add(p.getDrug());
            if (p.getPrescriptionEnum().toString().equals("AUTHENTICATED")) {
                drugString.add(p.getDrug().getName() + "(Authenticated by nurse " + p.getNurse().getFirstName() + " " + p.getNurse().getLastName() + ")");
            }
            else {
                drugString.add(p.getDrug().getName() + "(Hasn't been authenticated)");
            }
        }
        this.timeCreated = examinationReport.getTimeCreated();
        this.lastEdited = examinationReport.getLastEdited();
        this.drugs = drugs;
        this.drugString = drugString;
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

    public List<String> getDrugString() {
        return drugString;
    }

    public void setDrugString(List<String> drugString) {
        this.drugString = drugString;
    }
}
