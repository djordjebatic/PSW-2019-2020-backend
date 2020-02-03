package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.AppointmentType;
import com.example.pswbackend.dto.AppointmentTypeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentType,Long> {

    AppointmentType findOneById(Long id);
    AppointmentType findByName(String name);
    List<AppointmentType> findAll();
    List<AppointmentType> findByClinicId(Long clinicId);



}
