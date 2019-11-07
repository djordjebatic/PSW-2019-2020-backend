package domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class Ordination {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "number")
	@NonNull
	private int number;
	
	@Column(name = "isreserved")
	@NonNull
	private boolean isReserved;
	
	//svaka ordinacija ima vise pregleda
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Appointment appointment;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Clinic clinic ;
	
	public Ordination() {
		super();
	}

	public Ordination(long id, int number, boolean isReserved, Appointment appointment, Clinic clinic) {
		super();
		this.id = id;
		this.number = number;
		this.isReserved = isReserved;
		this.appointment = appointment;
		this.clinic = clinic;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isReserved() {
		return isReserved;
	}

	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
	
	
}
