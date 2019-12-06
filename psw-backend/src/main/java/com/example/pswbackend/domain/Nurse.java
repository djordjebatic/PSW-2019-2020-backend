package com.example.pswbackend.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.example.pswbackend.enums.UserStatus;

@Entity
@DiscriminatorValue(value="NURSE")
public class Nurse extends Account{
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Clinic clinic;
	
	@OneToMany(mappedBy = "nurse", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany(mappedBy = "nurse", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Prescription> prescriptions = new HashSet<>();
	
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

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

	public UserStatus getStatus() {
		return userStatus;
	}

	public void setStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
}
