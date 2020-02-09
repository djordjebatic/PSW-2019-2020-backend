package com.example.pswbackend.dto;

import com.example.pswbackend.domain.ExaminationReport;
import com.example.pswbackend.domain.Prescription;

import java.util.ArrayList;
import java.util.List;

public class ExaminationReportDTO {

    private String comment;
    private Long diagnosisId;
    private List<Long> drugIds;

    ExaminationReportDTO(){

    }

    public ExaminationReportDTO(String comment, Long diagnosisId, List<Long> drugIds) {
        this.comment = comment;
        this.diagnosisId = diagnosisId;
        this.drugIds = drugIds;
    }

    public ExaminationReportDTO(ExaminationReport examinationReport){
        this.comment = examinationReport.getComment();
        this.diagnosisId = examinationReport.getDiagnosis().getId();
        List<Long> drugs = new ArrayList<>();
        for (Prescription p : examinationReport.getPrescriptions()){
            drugs.add(p.getDrug().getId());
        }
        this.drugIds = drugs;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Long diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public List<Long> getDrugIds() {
        return drugIds;
    }

    public void setDrugIds(List<Long> drugIds) {
        this.drugIds = drugIds;
    }
}
