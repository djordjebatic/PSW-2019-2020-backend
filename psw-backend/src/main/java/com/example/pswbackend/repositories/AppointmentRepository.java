package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findOneById(Long id);
    List<Appointment> findAll();
    List<Appointment> findByClinicId(Long id);

    List<Appointment> findByDoctorsIdAndStatusIn(Long id, List<AppointmentStatus> appointmentStatus);
    List<Appointment> findByOrdinationIdAndStatusNotOrderByStartDateTime(Long id, AppointmentStatus appointmentStatus);
    List<Appointment> findByNurseIdAndStatusIn(Long id, List<AppointmentStatus> appointmentStatus);
    List<Appointment> findByNurseIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanAndStatusIn(Long id, LocalDateTime start, LocalDateTime end, List<AppointmentStatus> appointmentStatus);
    Appointment getByIdAndStatusNot(Long id, AppointmentStatus appointmentStatus);
    List<Appointment> findByStatus(AppointmentStatus appointmentStatus);
    List<Appointment> findByStatusIn(List<AppointmentStatus> statuses);
    Appointment findByPatientIdAndDoctorsIdAndDoctorsUserStatusAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanAndStatusIn(
            Long patientId, Long doctorsId, UserStatus userStatus, LocalDateTime start, LocalDateTime end, List<AppointmentStatus> appointmentEnums);

    List<Appointment> findByOrdinationIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndStatusIn(Long ordinationId, LocalDateTime start, LocalDateTime end, List<AppointmentStatus> statuses);
    List<Appointment> findByOrdinationId(Long ordinationId);
    List<Appointment> findByDoctorsIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndStatusIn(Long doctorId, LocalDateTime start, LocalDateTime end, List<AppointmentStatus> statuses);
}


