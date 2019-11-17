package com.example.pswbackend.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class AppointmentType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, columnDefinition = "VARCHAR(30)", nullable = false)
	private String label;

	@Column(nullable = false, scale = 2)
	private Double price;

	@OneToMany(mappedBy = "specialization", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<Doctor> doctors = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Clinic clinic;

	@OneToMany(mappedBy = "appointmentType", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Appointment> appointments = new HashSet<>();

	public AppointmentType(String label, Double price, Set<Doctor> doctors, Clinic clinic,
			Set<Appointment> appointments) {
		super();
		this.label = label;
		this.price = price;
		this.doctors = doctors;
		this.clinic = clinic;
		this.appointments = appointments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
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
}
