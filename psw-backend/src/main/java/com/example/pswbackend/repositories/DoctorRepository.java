package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByEmail(String email);
    List<Doctor> findByClinicId(long id);
}
