package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.PaidTimeOffDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaidTimeOffDoctorRepository extends JpaRepository<PaidTimeOffDoctor, Long> {
}
