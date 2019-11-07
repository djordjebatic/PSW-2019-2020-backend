package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Codebook {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany
	private Iterable<Diagnosis> diagnosis;
	
	@OneToMany
	private Iterable<Drug> drugs;
	
	public Codebook() {
		
	}	
	
	public Codebook(Long id, Iterable<Diagnosis> diagnosis, Iterable<Drug> drugs) {
		super();
		this.id = id;
		this.diagnosis = diagnosis;
		this.drugs = drugs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Iterable<Diagnosis> getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Iterable<Diagnosis> diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Iterable<Drug> getDrugs() {
		return drugs;
	}

	public void setDrugs(Iterable<Drug> drugs) {
		this.drugs = drugs;
	}
	
}