package com.example.pswbackend.services;

import com.example.pswbackend.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService {

    //TODO move appointmentRequestService methods here
    List<Appointment> getAppointments(Long ordinationId);
    List<Appointment> getDoctorAppointments(Long doctorId);
    List<Appointment> getNurseAppointments(Long nurseId);
    List<Appointment> getCanceledAppointments();
    List<Appointment> getAwaitingApprovalAppointments();
    List<Appointment> getPredefinedAwailableAppointments();
    List<Appointment> getPredefinedBookedAppointments();


    Appointment getAppointment(Long id);

    Appointment assignOrdination(Appointment appointment, Ordination ordination, Nurse nurse);
    Appointment cancelAppointment(Doctor doctor, Long appointmentId);

    void sendMail(Appointment appointment, Patient patient, Doctor doctor, Nurse nurse);

}
