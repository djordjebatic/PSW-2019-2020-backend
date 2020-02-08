package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.VoteClinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteClinicRepository extends JpaRepository<VoteClinic, Long> {


    List<VoteClinic> findByPatientId(Long id);
    List<VoteClinic> findByClinicId(Long id);
    VoteClinic findByPatientIdAndClinicId(Long patientId, Long clinicId);
}
