package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicRepository extends JpaRepository <Clinic, Long> {

    Clinic findOneById(Long id);
    List<Clinic> findByNameIgnoreCase(String name);

    List<Clinic> findAll();
}
