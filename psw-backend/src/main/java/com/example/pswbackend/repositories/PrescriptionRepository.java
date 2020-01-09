package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Prescription;
import com.example.pswbackend.enums.PrescriptionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findByNurseIdAndPrescriptionEnumNot(Long id, PrescriptionEnum prescriptionEnum);
    Prescription findOneById(Long id);
}
