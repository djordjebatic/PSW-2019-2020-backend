package com.example.pswbackend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Clinic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	private Set<Doctor> clinicAdmins = new HashSet<>();

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

	public Set<Doctor> getClinicAdmins() {
		return clinicAdmins;
	}

	public void setClinicAdmins(Set<Doctor> clinicAdmins) {
		this.clinicAdmins = clinicAdmins;
	}
	
}
