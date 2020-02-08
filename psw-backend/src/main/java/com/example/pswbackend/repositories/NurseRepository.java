package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
    Nurse findByEmail(String email);
    Nurse findOneById(Long id);
}
