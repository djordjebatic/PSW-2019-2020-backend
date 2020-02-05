package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.AppointmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest,Long> {

    @Query(value="DELETE FROM appointment_request ar WHERE ar.id = :id", nativeQuery = true)
    Boolean deleteOneById(Long id);
}
