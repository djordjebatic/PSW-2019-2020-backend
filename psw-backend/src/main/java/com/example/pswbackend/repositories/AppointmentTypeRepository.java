package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.AppointmentType;
import com.example.pswbackend.dto.AppointmentTypeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentType,Long> {

    AppointmentType findOneById(Long id);
    AppointmentType findByName(String name);
    List<AppointmentType> findAll();
    List<AppointmentType> findByClinicId(Long clinicId);

    @Query(value="DELETE FROM appointment_type at WHERE at.id = :id", nativeQuery = true)
    Boolean deleteOneById(Long id);

}
