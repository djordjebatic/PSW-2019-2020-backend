package com.example.pswbackend.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.example.pswbackend.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue(value="NURSE")
public class Nurse extends Account{

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Clinic clinic;

	@JsonManagedReference
	@OneToMany(mappedBy = "nurse", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<>();

	@JsonManagedReference
    @OneToMany(mappedBy = "nurse", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Prescription> prescriptions = new HashSet<>();
	
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @JsonIgnore
	@OneToOne(mappedBy = "nurse",cascade = CascadeType.ALL)
	private PaidTimeOffNurse paidTimeOffNurse;

	public Nurse(){
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Set<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public PaidTimeOffNurse getPaidTimeOffNurse() {
		return paidTimeOffNurse;
	}

	public void setPaidTimeOffNurse(PaidTimeOffNurse paidTimeOffNurse) {
		this.paidTimeOffNurse = paidTimeOffNurse;
	}
}
