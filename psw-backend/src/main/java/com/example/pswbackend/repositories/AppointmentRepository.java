package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Ordination;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findOneById(Long id);
    List<Appointment> findAll();
    List<Appointment> findByClinicId(Long id);
    List<Appointment> findByClinicIdAndStatus(Long id, AppointmentStatus appointmentStatus);

    List<Appointment> findByDoctorsIdAndStatusIn(Long id, List<AppointmentStatus> appointmentStatus);
    List<Appointment> findByOrdinationIdAndStatusNotOrderByStartDateTime(Long id, AppointmentStatus appointmentStatus);
    List<Appointment> findByNurseIdAndStatusIn(Long id, List<AppointmentStatus> appointmentStatus);
    List<Appointment> findByNurseIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanAndStatusIn(Long id, LocalDateTime start, LocalDateTime end, List<AppointmentStatus> appointmentStatus);
    @Query(value = "SELECT * FROM Appointment a WHERE a.start_date_time >= ?2 AND a.end_date_time < ?3 AND a.status IN ?4 AND EXISTS(SELECT * from a.doctors doc WHERE doc.id = ?1)", nativeQuery = true)
    List<Appointment> findByDoctorIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanAndStatusIn(Long id, LocalDateTime start, LocalDateTime end, List<AppointmentStatus> appointmentStatus);
    Appointment getByIdAndStatusNot(Long id, AppointmentStatus appointmentStatus);
    List<Appointment> findByStatus(AppointmentStatus appointmentStatus);
    List<Appointment> findByStatusIn(List<AppointmentStatus> statuses);
    Appointment findByPatientIdAndDoctorsIdAndDoctorsUserStatusAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanAndStatusIn(
            Long patientId, Long doctorsId, UserStatus userStatus, LocalDateTime start, LocalDateTime end, List<AppointmentStatus> appointmentEnums);

    @Query(value = "SELECT * FROM appointment a WHERE (a.start_date_time, a.end_date_time) OVERLAPS (?1,?2) AND a.ordination_id = ?3 AND (a.status = 'APPROVED' or a.status = 'PREDEF_BOOKED')", nativeQuery = true)
    List<Appointment> findByOrdinationIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqual(LocalDateTime start, LocalDateTime end,Long ordinationId);

    List<Appointment> findByOrdinationIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndStatusIn(Long ordinationId, LocalDateTime start, LocalDateTime end, List<AppointmentStatus> statuses);
    List<Appointment> findByOrdinationId(Long ordinationId);
    List<Appointment> findByDoctorsIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndStatusIn(Long doctorId, LocalDateTime start, LocalDateTime end, List<AppointmentStatus> statuses);
   
    List<Appointment> findByPatientId(Long id);

    @Query("SELECT DISTINCT a.patient FROM Appointment a where (a.clinic.id=?1)")
    List<Patient> findPatientsByClinicId(Long id);

}


