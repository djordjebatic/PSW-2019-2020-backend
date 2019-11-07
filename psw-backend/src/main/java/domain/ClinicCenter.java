package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class ClinicCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	@NonNull
	private String name;
	
	@Column(name = "adress")
	@NonNull
	private String adress;
	
	@ManyToOne
	private Iterable<Clinic> clinics;
	
	@OneToMany
	private Iterable<CCAdmin> ccAdmins;
	
	public ClinicCenter() {
		super();
	}

	public ClinicCenter(Long id, String name, String adress, Iterable<Clinic> clinics, Iterable<CCAdmin> ccAdmins) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.clinics = clinics;
		this.ccAdmins = ccAdmins;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Iterable<Clinic> getClinics() {
		return clinics;
	}

	public void setClinics(Iterable<Clinic> clinics) {
		this.clinics = clinics;
	}

	public Iterable<CCAdmin> getCcAdmins() {
		return ccAdmins;
	}

	public void setCcAdmins(Iterable<CCAdmin> ccAdmins) {
		this.ccAdmins = ccAdmins;
	}
	
	
	
	
}
