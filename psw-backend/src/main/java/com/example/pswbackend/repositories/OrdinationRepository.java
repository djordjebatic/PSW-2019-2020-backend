package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Ordination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdinationRepository extends JpaRepository<Ordination, Long> {

    Ordination findOneById(Long id);
    List<Ordination> findByClinicId(Long clinicId);

}
