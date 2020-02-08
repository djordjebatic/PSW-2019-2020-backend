package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.VoteDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteDoctorRepository extends JpaRepository<VoteDoctor, Long> {

     List<VoteDoctor> findByPatientId(Long id);
     List<VoteDoctor> findByDoctorId(Long id);
     VoteDoctor findByPatientIdAndDoctorId(Long patientId, Long doctorId);
}
