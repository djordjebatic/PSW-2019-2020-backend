package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    Diagnosis findByName(String name);
    Diagnosis findOneById(Long id);
}
