package com.example.pswbackend.domain;

import javax.persistence.*;

import com.example.pswbackend.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import com.example.pswbackend.enums.Status;

import java.util.Set;

@Entity
@DiscriminatorValue(value="PATIENT")
public class Patient extends Account{
	
	// TODO generate radnom number
	@Column(columnDefinition = "VARCHAR(13)", unique = true)
	@NonNull
	private String medicalNumber;

    @Enumerated(EnumType.STRING)
    private Status status;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Appointment> appointments;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private MedicalRecord medicalRecord;

	public Patient(){
	}

	public String getMedicalNumber() {
		return medicalNumber;
	}

	public void setMedicalNumber(String medicalNumber) {
		this.medicalNumber = medicalNumber;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public Status getPatientStatus() {
		return status;
	}

	public void setPatientStatus(Status status) {
		this.status = status;
	}
}