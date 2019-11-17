package com.example.pswbackend.domain;

import com.example.pswbackend.enums.UserStatus;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Doctor{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message = "Email is empty.")
    @Email(message ="Email is invalid.")
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    
    @NotEmpty(message = "First name is empty.")
    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String firstName;

    @NotEmpty(message = "Last name is empty.")
    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String lastName;

    @NotEmpty(message = "Phone number is empty.")
    @Size(min=9, max=10)
    @Pattern(regexp = "0[0-9]+")
    @Column(columnDefinition = "VARCHAR(11)", unique = true, nullable = false)
    private String phoneNumber;
    
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Clinic clinic;
	
	@ManyToMany(mappedBy = "doctors")
    private Set<Appointment> appointments = new HashSet<Appointment>();

	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@Column(nullable = false)
	private int stars;

	@Column(nullable = false)
	private int num_votes;

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public int getNum_votes() {
		return num_votes;
	}

	public void setNum_votes(int num_votes) {
		this.num_votes = num_votes;
	}

	public Doctor(@NotEmpty(message = "Email is empty.") @Email(message = "Email is invalid.") String email,
				  String password, @NotEmpty(message = "First name is empty.") String firstName,
				  @NotEmpty(message = "Last name is empty.") String lastName,
				  @NotEmpty(message = "Phone number is empty.") @Size(min = 9, max = 10) @Pattern(regexp = "0[0-9]+") String phoneNumber,
				  Clinic clinic, Set<Appointment> appointments) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.clinic = clinic;
		this.appointments = appointments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
}
