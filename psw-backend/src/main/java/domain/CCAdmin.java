package domain;

import javax.persistence.*;

import org.springframework.lang.NonNull;

@Entity
public class CCAdmin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Iterable<ClinicCenter> clinicCenters;

	public CCAdmin() {
		
	}
	
	public CCAdmin(Long id, String username, String password, String firstName, String lastName,
			Iterable<ClinicCenter> clinicCenters) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.clinicCenters = clinicCenters;
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

	public Iterable<ClinicCenter> getClinicCenters() {
		return clinicCenters;
	}

	public void setClinicCenters(Iterable<ClinicCenter> clinicCenters) {
		this.clinicCenters = clinicCenters;
	}
	
	
	
}
