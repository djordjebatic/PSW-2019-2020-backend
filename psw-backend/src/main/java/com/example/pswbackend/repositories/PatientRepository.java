package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByEmail(String email);
    List<Patient> findByStatus(Status status);
}
