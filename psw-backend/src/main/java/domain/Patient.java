package domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.lang.NonNull;

@Entity
public class Patient extends Account {
	

	@Column(name = "medicalNumber")
	@NonNull
	private String medicalNumber;
	

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Iterable<Appointment> appointments;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private MedicalRecord medicalRecord;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Iterable<Doctor> doctors;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Iterable<Nurse> nurses;
	
	public Patient() {
		super();
	}

	public Patient(Iterable<Appointment> appointments, MedicalRecord medicalRecord, Iterable<Doctor> doctors,
			Iterable<Nurse> nurses) {
		super();
		this.appointments = appointments;
		this.medicalRecord = medicalRecord;
		this.doctors = doctors;
		this.nurses = nurses;
	}


	public Iterable<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Iterable<Appointment> appointments) {
		this.appointments = appointments;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public Iterable<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Iterable<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Iterable<Nurse> getNurses() {
		return nurses;
	}

	public void setNurses(Iterable<Nurse> nurses) {
		this.nurses = nurses;
	}
	
	public String getMedicalNumber() {
		return medicalNumber;
	}

	public void setMedicalNumber(String medicalNumber) {
		this.medicalNumber = medicalNumber;
	}
	
	

}
