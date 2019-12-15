package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findOneById(Long id);
    List<Appointment> findAll();
    List<Appointment> findByClinicId(Long id);

}


