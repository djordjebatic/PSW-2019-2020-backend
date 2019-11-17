package com.example.pswbackend.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(unique = true, columnDefinition = "VARCHAR(30)", nullable = false)
	private String code;
	
	@Column(unique = true, columnDefinition = "VARCHAR(30)", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "diagnosis", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<ExaminationReport> examinationReports = new HashSet<>();

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

	public Set<ExaminationReport> getExaminationReports() {
		return examinationReports;
	}

	public void setExaminationReports(Set<ExaminationReport> examinationReports) {
		this.examinationReports = examinationReports;
	}

	
	
}
