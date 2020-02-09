package com.example.pswbackend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Clinic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	@Column(name = "version_number", columnDefinition = "integer DEFAULT 0", nullable = false)
	private Long version = 0L;
	
    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

    @Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private int stars;

	@Column(nullable = false)
	private int num_votes;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Doctor> doctors = new HashSet<>();

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Nurse> nurses = new HashSet<>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private BusinessReport businessReport;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Ordination> ordinations = new HashSet<>();

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ClinicAdmin> clinicAdmins = new HashSet<>();

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AppointmentType> appointmentTypes = new ArrayList<>();

	@JsonManagedReference
	@OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<AppointmentRequest> appointmentRequests = new ArrayList<>();

	@JsonManagedReference
	@OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Appointment> appointments = new HashSet<>();
  
	@Column
	private double latitude;

	@Column
	private double longitude;

	@JsonManagedReference
	@OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<VoteClinic> voteClinic = new HashSet<>();

	public Clinic(String name, String description, String address, String city) {
		this.name = name;
		this.description = description;
		this.address = address;
		this.city = city;
		this.doctors = new HashSet<>();
		this.nurses = new HashSet<>();
		this.ordinations = new HashSet<>();
		this.clinicAdmins = new HashSet<>();
	}

	public Clinic() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Set<Nurse> getNurses() {
		return nurses;
	}

	public void setNurses(Set<Nurse> nurses) {
		this.nurses = nurses;
	}

	public BusinessReport getBusinessReport() {
		return businessReport;
	}

	public void setBusinessReport(BusinessReport businessReport) {
		this.businessReport = businessReport;
	}

	public Set<Ordination> getOrdinations() {
		return ordinations;
	}

	public void setOrdinations(Set<Ordination> ordinations) {
		this.ordinations = ordinations;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Set<ClinicAdmin> getClinicAdmins() {
		return clinicAdmins;
	}

	public void setClinicAdmins(Set<ClinicAdmin> clinicAdmins) {
		this.clinicAdmins = clinicAdmins;
	}

	public List<AppointmentType> getAppointmentTypes() {
		return appointmentTypes;
	}

	public void setAppointmentTypes(List<AppointmentType> appointmentTypes) {
		this.appointmentTypes = appointmentTypes;
	}

	public List<AppointmentRequest> getAppointmentRequests() {
		return appointmentRequests;
	}

	public void setAppointmentRequests(List<AppointmentRequest> appointmentRequests) {
		this.appointmentRequests = appointmentRequests;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
}
