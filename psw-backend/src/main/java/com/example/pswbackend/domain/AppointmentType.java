package com.example.pswbackend.domain;

import com.example.pswbackend.enums.AppointmentEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class AppointmentType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, columnDefinition = "VARCHAR(30)", nullable = false)
	private String name;

	//@Column(nullable = false, scale = 2)
	//private Double price;

	@OneToMany(mappedBy = "specialization", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<Doctor> doctors;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Clinic clinic;

	@OneToMany(mappedBy = "appointmentType", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<AppointmentPrice> appointmentPrices;

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
