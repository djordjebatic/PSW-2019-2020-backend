package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.dto.ClinicAdminDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicAdminRepository extends JpaRepository <ClinicAdmin, Long> {

    ClinicAdmin findOneById(Long id);
    ClinicAdminDTO findByEmail(String email);

    List<ClinicAdmin> findAll();
    List<ClinicAdmin> findByClinicId(Long id);

}