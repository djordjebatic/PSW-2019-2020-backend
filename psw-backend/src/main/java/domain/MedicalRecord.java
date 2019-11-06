package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class MedicalRecord {

	@Id
	private Long id;
	
	@OneToOne
	private Patient patient;
	
	@ManyToMany
	private Iterable<Diagnosis> diagnosis;
	
	@ManyToMany
	private Iterable<Drug> drugs;
	
	//svaki karton ima vise doktora
	@OneToMany
	private Iterable<Doctor> doctors;
	
	//svaki karton ima vise pregleda
	@OneToMany
	private Iterable<Appointment> apointmentHistory;
	
	public MedicalRecord() {
		super();
	}
	
	public MedicalRecord(Long id, Patient patient, Iterable<Diagnosis> diagnosis, Iterable<Drug> drugs,
			Iterable<Doctor> doctors, Iterable<Appointment> apointmentHistory) {
		super();
		this.id = id;
		this.patient = patient;
		this.diagnosis = diagnosis;
		this.drugs = drugs;
		this.doctors = doctors;
		this.apointmentHistory = apointmentHistory;
	}

	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Iterable<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(Iterable<Doctor> doctors) {
		this.doctors = doctors;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Iterable<Diagnosis> getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(Iterable<Diagnosis> diagnosis) {
		this.diagnosis = diagnosis;
	}
	public Iterable<Drug> getDrugs() {
		return drugs;
	}
	public void setDrugs(Iterable<Drug> drugs) {
		this.drugs = drugs;
	}
	public Iterable<Appointment> getApointmentHistory() {
		return apointmentHistory;
	}
	public void setApointmentHistory(Iterable<Appointment> apointmentHistory) {
		this.apointmentHistory = apointmentHistory;
	}
	
	
	
}
