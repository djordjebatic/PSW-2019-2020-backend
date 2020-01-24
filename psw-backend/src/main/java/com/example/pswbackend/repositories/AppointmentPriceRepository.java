package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.AppointmentPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentPriceRepository extends JpaRepository<AppointmentPrice, Long> {

    List<AppointmentPrice> findByAppointmentTypeId(Long appointmentTypeId);
}
