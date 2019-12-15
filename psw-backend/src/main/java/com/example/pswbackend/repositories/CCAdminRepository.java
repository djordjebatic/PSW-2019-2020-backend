package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.CCAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CCAdminRepository extends JpaRepository<CCAdmin, Long> {

    CCAdmin findOneById(Long id);
    CCAdmin findByEmail(String email);
    List<CCAdmin> findAll();
}