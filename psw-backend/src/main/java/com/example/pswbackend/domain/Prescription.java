package com.example.pswbackend.domain;

import com.example.pswbackend.enums.PrescriptionEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Prescription {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private ExaminationReport examinationReport;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Nurse nurse;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Drug drug;

	@Column
	private PrescriptionEnum prescriptionEnum;

    public Prescription(){

	}

	public Prescription(Drug drug, ExaminationReport examinationReport, Nurse nurse) {
		this.drug = drug;
		this.examinationReport = examinationReport;
		this.nurse = nurse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public ExaminationReport getExaminationReport() {
		return examinationReport;
	}

	public void setExaminationReport(ExaminationReport examinationReport) {
		this.examinationReport = examinationReport;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	public PrescriptionEnum getPrescriptionEnum() {
		return prescriptionEnum;
	}

	public void setPrescriptionEnum(PrescriptionEnum prescriptionEnum) {
		this.prescriptionEnum = prescriptionEnum;
	}
}
