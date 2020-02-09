package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.enums.PaidTimeOffStatus;
import org.apache.tomcat.jni.Local;
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

    @Query(value = "SELECT a.id FROM appointment a JOIN appointed_doctors ad ON ad.appointment_id = a.id JOIN account d ON d.id = ad.doctor_id WHERE (a.start_date_time, a.end_date_time) OVERLAPS (?1,?2) AND d.id = ?3 AND (a.status = 'APPROVED' or a.status = 'PREDEF_BOOKED')", nativeQuery = true)
    List<Long> getOverlappingDoctorAppointments(LocalDateTime start, LocalDateTime end, Long id);

    Doctor findOneById(Long id);

}
