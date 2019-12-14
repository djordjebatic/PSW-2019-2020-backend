package com.example.pswbackend.domain;

import com.example.pswbackend.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="CLINIC_ADMIN")
public class ClinicAdmin extends Account{
    
    //FetchType must be EAGER because of editing ClinicAdministrator
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Clinic clinic;
    
    @OneToMany(mappedBy = "clinicAdmin", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<>();

	@JsonManagedReference
	@OneToMany(mappedBy = "clinicAdmin", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<AppointmentRequest> appointmentRequests = new HashSet<>();

	@OneToMany(mappedBy = "clinicAdmin", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Appointment> predefinedAppointments = new HashSet<>();

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

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public Set<Appointment> getPredefinedAppointments() {
		return predefinedAppointments;
	}

	public void setPredefinedAppointments(Set<Appointment> predefinedAppointments) {
		this.predefinedAppointments = predefinedAppointments;
	}

	public Set<AppointmentRequest> getAppointmentRequests() {
		return appointmentRequests;
	}

	public void setAppointmentRequests(Set<AppointmentRequest> appointmentRequests) {
		this.appointmentRequests = appointmentRequests;
	}
}
