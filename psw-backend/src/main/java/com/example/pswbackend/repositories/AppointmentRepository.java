package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findOneById(Long id);
    List<Appointment> findAll();
    List<Appointment> findByClinicId(Long id);

    List<Appointment> findByDoctorsIdAndStatusNot(Long id, AppointmentStatus appointmentStatus);
    List<Appointment> findByOrdinationIdAndStatusNotOrderByStartDateTime(Long id, AppointmentStatus appointmentStatus);
    List<Appointment> findByNurseIdAndStatusNot(Long id, AppointmentStatus appointmentStatus);
    Appointment getByIdAndStatusNot(Long id, AppointmentStatus appointmentStatus);
    List<Appointment> findByStatus(AppointmentStatus appointmentStatus);

}


