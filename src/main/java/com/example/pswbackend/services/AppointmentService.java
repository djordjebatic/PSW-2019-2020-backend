package com.example.pswbackend.services;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface AppointmentService {

    //TODO move appointmentRequestService methods here
    List<AppointmentCalendarDTO> getDoctorAppointments(Long doctorId);
    List<AppointmentCalendarDTO> getNurseAppointments(Long nurseId);
    List<AppointmentCalendarDTO> getOrdinationAppointments(Long ordinationId);
    List<Appointment> getAwaitingApprovalAppointments();
    Appointment getOngoingAppointment(Long patientId, Long doctorId, LocalDateTime startDateTime);
    List<Appointment> getOrdinationAppointmentsDuringTheDay(Long ordinationId, LocalDateTime day);
    List<Appointment> getDoctorAppointmentsDuringTheDay(Long ordinationId, LocalDateTime day);
    //List<Appointment> getAppointmentsAfter(Long appointmentId);

    Appointment getAppointment(Long id);

    Appointment assignOperationOrdination(Appointment appointment, Ordination ordination, Set<Doctor> doctors);

    void sendCancelationMail(Appointment appointment, Patient patient, Doctor doctor, Nurse nurse);
    void sendOperationMail(Appointment appointment);


     List<AppointmentHistoryDTO> getHistoryApp(long id);
     
    List<Ordination> getAvailableOrdinations(AvailableAppointmentDTO dto);

    Appointment createNew(NewAppointmentDTO dto);
    List<PredefinedAppointmentDTO> getFutureCancelAppointments(Long id);
    List<PredefinedAppointmentDTO> getFutureFixAppointments(Long id);
    Appointment cancelAppointmentP(Long appointmentId);

}
