package dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

import domain.Country;

public class UserSignUpDTO {
	
	@NonNull
	@NotEmpty
	private String email;
	
	@NonNull
	@NotEmpty
	private String password;
	
	@NonNull
	@NotEmpty
	private String firstName;
	
	@NonNull
	@NotEmpty
	private String lastName;
	
	@NonNull
	@NotEmpty
	private String medicalNumber;
	
	@NonNull
	@NotEmpty
	private String address;
	
	@NonNull
	@NotEmpty
	private String city;
	
	@NonNull
	@NotEmpty
	private Country country;
	
	@NonNull
	@NotEmpty
	private String phoneNumber;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
		
	public String getMedicalNumber() {
		return medicalNumber;
	}

	public void setMedicalNumber(String medicalNumber) {
		this.medicalNumber = medicalNumber;
	}


}
