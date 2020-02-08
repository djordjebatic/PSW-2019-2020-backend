package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByEmail(String email);

    List<Doctor> findAll();
    List<Doctor> findByClinicId(Long id);

    @Query(value="DELETE FROM account a WHERE a.id = :id", nativeQuery = true)
    Boolean deleteOneById(Long id);

    @Query(value="SELECT * FROM account a WHERE a.work_time_start <= ?1 AND a.work_time_end > ?2 AND a.clinic_id = ?3", nativeQuery = true)
    List<Doctor> findByWorkTimeStartGreaterThanEqualAndWorkTimeEndLowerThan(LocalTime start, LocalTime end,Long id);
    List<Doctor> findByClinicIdAndSpecializationId(Long clinicId, Long specializationId);

    Doctor findOneById(Long id);

}
