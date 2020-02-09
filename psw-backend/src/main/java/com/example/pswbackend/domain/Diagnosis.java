package com.example.pswbackend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, columnDefinition = "VARCHAR(30)", nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR", nullable = false)
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "diagnosis", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<ExaminationReport> examinationReports = new HashSet<>();

    @Version
    @Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
    private long version = 0L;

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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
