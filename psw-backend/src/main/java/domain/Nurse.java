package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Nurse extends Account {
	
	
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Clinic clinic;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Iterable<Prescription> prescriptions;
	
	public Nurse() {
		super();
	}

	public Nurse(Clinic clinic, Iterable<Prescription> prescriptions) {
		super();
		this.clinic = clinic;
		this.prescriptions = prescriptions;
	}


	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Iterable<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(Iterable<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
	
	
	

}
