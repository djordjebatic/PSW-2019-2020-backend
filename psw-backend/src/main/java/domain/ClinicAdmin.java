package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class ClinicAdmin extends Account {


	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Iterable<Clinic> clinics;

	public ClinicAdmin(Iterable<Clinic> clinics) {
		super();
		this.clinics = clinics;
	}

	
	public Iterable<Clinic> getClinics() {
		return clinics;
	}

	public void setClinics(Iterable<Clinic> clinics) {
		this.clinics = clinics;
	}
	
	
	
	
}
