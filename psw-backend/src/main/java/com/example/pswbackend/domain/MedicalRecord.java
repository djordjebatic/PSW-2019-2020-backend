package com.example.pswbackend.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class MedicalRecord {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Long patientId;
	
    @Column
    private Integer height;

    @Column
    private Integer weight;

    @Column(columnDefinition = "VARCHAR(3)")
    private String bloodType;
	
    @Column
    private String allergies;
    
	@OneToMany(mappedBy = "medicalRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ExaminationReport> examinationReports;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public Iterable<ExaminationReport> getExaminationReports() {
		return examinationReports;
	}

	public void setExaminationReports(Set<ExaminationReport> examinationReports) {
		this.examinationReports = examinationReports;
	}	
}