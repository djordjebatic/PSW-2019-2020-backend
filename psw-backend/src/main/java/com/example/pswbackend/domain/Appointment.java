package com.example.pswbackend.domain;
import com.example.pswbackend.enums.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private AppointmentPrice price;

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	@Column(nullable = false)
	private LocalDateTime startDateTime;

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	@Column(nullable = false)
	private LocalDateTime endDateTime;

	@Enumerated(EnumType.STRING)
    private AppointmentStatus status;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Ordination ordination;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "appointed_doctors", joinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"))
	private Set<Doctor> doctors = new HashSet<>();

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Patient patient;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Nurse nurse;

	@JsonBackReference
	@ManyToOne(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
	private Clinic clinic;

	@JsonIgnore
	@OneToOne(mappedBy = "appointment",cascade = CascadeType.ALL)
    private ExaminationReport examinationReport;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	private ClinicAdmin clinicAdmin;

	@Column
    private Integer discount;

	@Version
	@Column(name = "version_number", columnDefinition = "integer DEFAULT 0", nullable = false)
	private Long version = 0L;


	public Appointment() {
		super();
		doctors = new HashSet<Doctor>();
	}

	public Appointment(Long id, AppointmentPrice price, Ordination ordination, Clinic clinic, Set<Doctor> doctors, AppointmentStatus status, Patient patient, Nurse nurse, LocalDateTime startDateTime, LocalDateTime endDateTime){
		this.id = id;
		this.price = price;
		this.ordination = ordination;
		this.clinic = clinic;
		this.doctors = doctors;
		this.status = status;
		this.patient = patient;
		this.nurse = nurse;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Clinic getClinic() { return clinic; }

	public void setClinic(Clinic clinic) { this.clinic = clinic; }

	public ClinicAdmin getClinicAdmin() {
		return clinicAdmin;
	}

	public void setClinicAdmin(ClinicAdmin clinicAdmin) {
		this.clinicAdmin = clinicAdmin;
	}

	public AppointmentPrice getPrice() {
		return price;
	}

	public void setPrice(AppointmentPrice price) {
		this.price = price;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
