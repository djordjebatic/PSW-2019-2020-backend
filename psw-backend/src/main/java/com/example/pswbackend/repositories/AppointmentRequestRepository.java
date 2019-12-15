package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.AppointmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest,Long> {
}
