package com.example.pswbackend.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class BusinessReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Clinic clinic;
	
	@Column(name = "avgClinicScore")
	private Double avgClinicScore;
	
	@Column(name = "avgDoctorScore")
	private Double avgDoctorScore;
	
	public BusinessReport() {
		super();
	}

	public BusinessReport(long id, Clinic clinic, Double avgClinicScore, Double avgDoctorScore) {
		super();
		this.id = id;
		this.clinic = clinic;
		this.avgClinicScore = avgClinicScore;
		this.avgDoctorScore = avgDoctorScore;
		//this.appointments = appointments;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Double getAvgClinicScore() {
		return avgClinicScore;
	}

	public void setAvgClinicScore(Double avgClinicScore) {
		this.avgClinicScore = avgClinicScore;
	}

	public Double getAvgDoctorScore() {
		return avgDoctorScore;
	}

	public void setAvgDoctorScore(Double avgDoctorScore) {
		this.avgDoctorScore = avgDoctorScore;
	}

	
	
}
