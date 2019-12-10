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
	private String name;

	@Column(columnDefinition = "VARCHAR", nullable = false)
	private String description;

	@OneToMany(mappedBy = "diagnosis", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<ExaminationReport> examinationReports = new HashSet<>();

	public Diagnosis(){

	}

	public Diagnosis(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
