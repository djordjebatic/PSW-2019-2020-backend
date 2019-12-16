package com.example.pswbackend.domain;

import com.example.pswbackend.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue(value="DOCTOR")
public class Doctor extends Account {

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Clinic clinic;
	
	@ManyToMany(mappedBy = "doctors")
    private Set<Appointment> appointments = new HashSet<Appointment>();

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AppointmentRequest> appointmentRequest = new HashSet<>();

	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;

	@Column
	private int stars;

	@Column
	private int num_votes;

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public int getNum_votes() {
		return num_votes;
	}

	public void setNum_votes(int num_votes) {
		this.num_votes = num_votes;
	}

	public Doctor(){
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

	public Set<AppointmentRequest> getAppointmentRequest() {
		return appointmentRequest;
	}

	public void setAppointmentRequest(Set<AppointmentRequest> appointmentRequest) {
		this.appointmentRequest = appointmentRequest;
	}
}