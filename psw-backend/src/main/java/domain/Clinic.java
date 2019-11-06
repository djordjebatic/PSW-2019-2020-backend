package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.lang.NonNull;

@Entity
public class Clinic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	@NonNull
	private String name;
	
	@Column(name = "adress")
	@NonNull
	private String adress;
	
	@Column(name = "score")
	private String score;
	
	@OneToMany
	private Iterable<Doctor> doctors;
	
	@OneToMany
	private Iterable<Nurse> nurses;
	
	@OneToOne
	private BusinessReport businessReport;
	
	@OneToMany
	private Iterable<Ordination> ordinations;
	
	@OneToMany
	private Iterable<Patient> patients;
	
	@OneToMany
	private Iterable<ClinicAdmin> clinicAdmins;
	
	public Clinic() {
		super();
	}

	public Clinic(Long id, String name, String adress, String score, Iterable<Doctor> doctors, Iterable<Nurse> nurses,
			BusinessReport businessReport, Iterable<Ordination> ordinations, Iterable<Patient> patients,
			Iterable<ClinicAdmin> clinicAdmins) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.score = score;
		this.doctors = doctors;
		this.nurses = nurses;
		this.businessReport = businessReport;
		this.ordinations = ordinations;
		this.patients = patients;
		this.clinicAdmins = clinicAdmins;
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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
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

	public BusinessReport getBusinessReport() {
		return businessReport;
	}

	public void setBusinessReport(BusinessReport businessReport) {
		this.businessReport = businessReport;
	}

	public Iterable<Ordination> getOrdinations() {
		return ordinations;
	}

	public void setOrdinations(Iterable<Ordination> ordinations) {
		this.ordinations = ordinations;
	}

	public Iterable<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Iterable<Patient> patients) {
		this.patients = patients;
	}

	public Iterable<ClinicAdmin> getClinicAdmins() {
		return clinicAdmins;
	}

	public void setClinicAdmins(Iterable<ClinicAdmin> clinicAdmins) {
		this.clinicAdmins = clinicAdmins;
	}



	
	

	
}
