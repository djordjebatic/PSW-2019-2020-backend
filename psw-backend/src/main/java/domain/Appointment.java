package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(name = "type")
	@NonNull
	private String type;
	
	@Column(name = "date")
	@NonNull
	private String date;
	
	@Column(name = "time")
	@NonNull
	private String time;
	
	@ManyToOne
	private Patient patient;
	
	@ManyToOne
	private Ordination ordination;
	
	@ManyToOne
	private MedicalRecord medicalRecord;
	
	public Appointment() {
		super();
	}

	public Appointment(long id, String type, String date, String time, Patient patient, Ordination ordination,
			MedicalRecord medicalRecord) {
		super();
		this.id = id;
		this.type = type;
		this.date = date;
		this.time = time;
		this.patient = patient;
		this.ordination = ordination;
		this.medicalRecord = medicalRecord;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Ordination getOrdination() {
		return ordination;
	}

	public void setOrdination(Ordination ordination) {
		this.ordination = ordination;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
	
}
