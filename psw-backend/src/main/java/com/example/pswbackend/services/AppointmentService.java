package com.example.pswbackend.services;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AppointmentCalendarDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface AppointmentService {

    //TODO move appointmentRequestService methods here
    List<Appointment> getAppointments(Long ordinationId);
    List<AppointmentCalendarDTO> getDoctorAppointments(Long doctorId);
    List<AppointmentCalendarDTO> getNurseAppointments(Long nurseId);
    List<Appointment> getCanceledAppointments();
    List<Appointment> getAwaitingApprovalAppointments();
    List<Appointment> getPredefinedAwailableAppointments();
    List<Appointment> getPredefinedBookedAppointments();
    Appointment getOngoingAppointment(Long patientId, Long doctorId, LocalDateTime startDateTime);

    Appointment getAppointment(Long id);

    Appointment assignOrdination(Appointment appointment, Ordination ordination, Nurse nurse);
    Appointment assignOperationRoom(Appointment appointment, Ordination ordination);

    Appointment cancelAppointment(Doctor doctor, Long appointmentId);

    void sendCancelationMail(Appointment appointment, Patient patient, Doctor doctor, Nurse nurse);
    void sendOperationMail(Appointment appointment);

}
