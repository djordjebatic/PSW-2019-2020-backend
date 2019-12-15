package com.example.pswbackend.domain;

import com.example.pswbackend.enums.AppointmentEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ordination {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
  @Column(unique = true, columnDefinition = "VARCHAR(30)", nullable = false)
  private String number;

  @Enumerated(EnumType.STRING)
  private AppointmentEnum type;

  @JsonManagedReference
  @OneToMany(mappedBy = "ordination", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  private List<Appointment> appointments;
	
  @JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Clinic clinic ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public AppointmentEnum getType() {
		return type;
	}

	public void setType(AppointmentEnum type) {
		this.type = type;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
}
