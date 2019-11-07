package domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.lang.NonNull;

@Entity
public class Nurse {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
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
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Clinic clinic;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Iterable<Prescription> prescriptions;
	
	public Nurse() {
		super();
	}

	public Nurse(long id, String username, String password, String firstName, String lastName, String email,
			Clinic clinic, Iterable<Prescription> prescriptions) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.clinic = clinic;
		this.prescriptions = prescriptions;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
