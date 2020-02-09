package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.ClinicAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicAdminRepository extends JpaRepository <ClinicAdmin, Long> {

    ClinicAdmin findOneById(Long id);
    ClinicAdmin findOneById(long id);

    List<ClinicAdmin> findAll();
    List<ClinicAdmin> findByClinicId(Long id);

}
