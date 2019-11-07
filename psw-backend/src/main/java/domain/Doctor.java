package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

@Entity
public class Doctor {

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
	
	@Column(name = "score")
	private String score;
	
	@ManyToMany
    private Iterable<Patient> patients;
	
	@ManyToOne
	private Clinic clinic;

	public Doctor() {
		super();
	}

	public Doctor(Long id, String username, String password, String firstName, String lastName, String email,
			String score, Iterable<Patient> patients, Clinic clinic) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.score = score;
		this.patients = patients;
		this.clinic = clinic;
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
