package domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Doctor extends Account {

	
	@Column(name = "score")
	private String score;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Iterable<Patient> patients;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Clinic clinic;

	public Doctor() {
		super();
	}

	public Doctor(String score, Iterable<Patient> patients, Clinic clinic) {
		super();

		this.score = score;
		this.patients = patients;
		this.clinic = clinic;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Iterable<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Iterable<Patient> patients) {
		this.patients = patients;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
	
	
	
	
		

}
