package domain;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

public class Drug {

	@Id
	private Long id;
	
	@Column(name = "code")
	@NonNull
	private String code;
	
	@Column(name = "name")
	@NonNull
	private String name;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
