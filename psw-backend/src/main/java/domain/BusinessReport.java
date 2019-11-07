package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class BusinessReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@OneToOne
	private Clinic clinic;
	
	@Column(name = "avgClinicScore")
	private Double avgClinicScore;
	
	@Column(name = "avgDoctorScore")
	private Double avgDoctorScore;
	
	@OneToMany
	private Iterable<Appointment> appointments;
	
	public BusinessReport() {
		super();
	}

	public BusinessReport(long id, Clinic clinic, Double avgClinicScore, Double avgDoctorScore,
			Iterable<Appointment> appointments) {
		super();
		this.id = id;
		this.clinic = clinic;
		this.avgClinicScore = avgClinicScore;
		this.avgDoctorScore = avgDoctorScore;
		this.appointments = appointments;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Double getAvgClinicScore() {
		return avgClinicScore;
	}

	public void setAvgClinicScore(Double avgClinicScore) {
		this.avgClinicScore = avgClinicScore;
	}

	public Double getAvgDoctorScore() {
		return avgDoctorScore;
	}

	public void setAvgDoctorScore(Double avgDoctorScore) {
		this.avgDoctorScore = avgDoctorScore;
	}

	public Iterable<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Iterable<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	
}
