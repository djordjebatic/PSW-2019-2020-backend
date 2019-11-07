package domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.lang.NonNull;

@Entity
public class Patient {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username")
	@NonNull
	private String username;
	
	@Column(name = "password")
	@NonNull
	private String password;
	
	@Column(name = "firstName")
	@NonNull
	private String firstName;
	
	@Column(name = "lastName")
	@NonNull
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
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

	public Patient(Long id, String username, String password, String firstName, String lastName, String email,
			Iterable<Appointment> appointments, MedicalRecord medicalRecord, Iterable<Doctor> doctors,
			Iterable<Nurse> nurses) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.appointments = appointments;
		this.medicalRecord = medicalRecord;
		this.doctors = doctors;
		this.nurses = nurses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	
	

}
