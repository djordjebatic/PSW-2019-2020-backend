package com.example.pswbackend.domain;

import java.util.HashSet;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Enumerated(EnumType.STRING)
	private AppointmentEnum type;
	
	@Column(nullable = false)
	private String date;
	
	@Column(nullable = false)
	private String time;
	
	@Column(nullable = false)
    private float price;

	@Column(nullable = false)
	private int duration;
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getDuration() { return duration; }

	public void setDuration(int duration) { this.duration = duration; }

	@Enumerated(EnumType.STRING)
    private AppointmentStatus status;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Ordination ordination;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Doctor doctor;
	
	@ManyToMany
    @JoinTable(name = "examining", joinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"))
    private Set<Doctor> doctors = new HashSet<Doctor>();

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Patient patient;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Nurse nurse;
	
	@OneToOne(mappedBy = "appointment",cascade = CascadeType.ALL)
    private ExaminationReport examinationReport;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private MedicalRecord medicalRecord;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	private ClinicAdmin clinicAdmin;

	@JsonBackReference
	@ManyToOne(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
	private Clinic clinic;

	@Column
    private Integer discount;
	
	@Column
    private Integer doctorRating;

    @Column
    private Integer clinicRating;
    
	public Appointment() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AppointmentEnum getType() {
		return type;
	}

	public void setType(AppointmentEnum type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public Ordination getOrdination() {
		return ordination;
	}

	public void setOrdination(Ordination ordination) {
		this.ordination = ordination;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	public ExaminationReport getExaminationReport() {
		return examinationReport;
	}

	public void setExaminationReport(ExaminationReport examinationReport) {
		this.examinationReport = examinationReport;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getDoctorRating() {
		return doctorRating;
	}

	public void setDoctorRating(Integer doctorRating) {
		this.doctorRating = doctorRating;
	}

	public Integer getClinicRating() {
		return clinicRating;
	}

	public void setClinicRating(Integer clinicRating) {
		this.clinicRating = clinicRating;
	}

	public ClinicAdmin getClinicAdmin() {
		return clinicAdmin;
	}

	public void setClinicAdmin(ClinicAdmin clinicAdmin) {
		clinicAdmin = clinicAdmin;
	}

	public Clinic getClinic() { return clinic; }

	public void setClinic(Clinic clinic) { this.clinic = clinic; }
}
