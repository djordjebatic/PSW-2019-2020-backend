package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class CCAdmin extends Account {

	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Iterable<ClinicCenter> clinicCenters;

	public CCAdmin() {
		
	}
	
	public CCAdmin(Iterable<ClinicCenter> clinicCenters) {
		super();

		this.clinicCenters = clinicCenters;
	}

	public Iterable<ClinicCenter> getClinicCenters() {
		return clinicCenters;
	}

	public void setClinicCenters(Iterable<ClinicCenter> clinicCenters) {
		this.clinicCenters = clinicCenters;
	}
	
	
	
}
