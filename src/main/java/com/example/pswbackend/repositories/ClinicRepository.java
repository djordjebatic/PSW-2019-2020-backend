package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClinicRepository extends JpaRepository <Clinic, Long> {

    Clinic findOneById(Long id);
    List<Clinic> findByNameIgnoreCase(String name);
    Clinic findByName(String name);

    List<Clinic> findAll();

    @Query(value = "SELECT sum(p.price) from appointment a JOIN appointment_price p ON a.price_id = p.id WHERE a.clinic_id = ?1 AND (a.status = 'APPROVED' OR a.status = 'PREDEF_BOOKED')", nativeQuery = true)
    double getIncomeOfTheClinic(Long id);
}
