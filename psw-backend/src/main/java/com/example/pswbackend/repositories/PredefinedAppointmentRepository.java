package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredefinedAppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findOneById(Long id);
    List<Appointment> findAll();

}


