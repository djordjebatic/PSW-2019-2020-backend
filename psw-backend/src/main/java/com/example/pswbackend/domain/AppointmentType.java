package com.example.pswbackend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AppointmentType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "VARCHAR(30)", nullable = false)
	private String name;

	//@Column(nullable = false, scale = 2)
	//private Double price;
	@JsonManagedReference
	@OneToMany(mappedBy = "specialization", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Doctor> doctors;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Clinic clinic;

	@JsonManagedReference
	@OneToMany(mappedBy = "appointmentType", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<AppointmentPrice> appointmentPrices;

	public AppointmentType() {

	}

	public AppointmentType(String name, Clinic clinic) {
		this.name = name;
		this.clinic = clinic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set<AppointmentPrice> getAppointmentPrices() {
		return appointmentPrices;
	}

	public void setAppointmentPrices(Set<AppointmentPrice> appointmentPrices) {
		this.appointmentPrices = appointmentPrices;
	}
}
