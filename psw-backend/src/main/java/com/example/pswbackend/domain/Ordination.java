package com.example.pswbackend.domain;

import com.example.pswbackend.enums.AppointmentEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ordination {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Version
	@Column(name = "version_number", columnDefinition = "integer DEFAULT 0", nullable = false)
	private Long version = 0L;
	
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

	public Ordination(){
		this.appointments = new ArrayList();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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
