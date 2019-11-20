package com.example.pswbackend.domain;

import com.example.pswbackend.enums.UserStatus;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@DiscriminatorValue("ClinicAdmin")
public class ClinicAdmin extends Account{
    
    //FetchType must be EAGER because of editing ClinicAdministrator
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Clinic clinic;
    
    @OneToMany(mappedBy = "clinicAdmin", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<>();

	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;

	public ClinicAdmin() {
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

	public UserStatus getStatus() {
		return userStatus;
	}

	public void setStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
}
