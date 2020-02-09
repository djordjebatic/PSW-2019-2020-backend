package com.example.pswbackend.dto;

public class ExaminationReportForPatientDTO {

    private String comment;
    private String diagnosis;
    private String lastEdited;

    public ExaminationReportForPatientDTO(String comment, String diagnosis, String lastEdited) {
        this.comment = comment;
        this.diagnosis = diagnosis;
        this.lastEdited = lastEdited;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(String lastEdited) {
        this.lastEdited = lastEdited;
    }
}
