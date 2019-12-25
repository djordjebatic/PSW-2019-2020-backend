package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.enums.UserStatus;
import com.example.pswbackend.repositories.AppointmentRepository;
import com.example.pswbackend.services.AppointmentService;
import com.example.pswbackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    EmailService emailService;

    @Override
    public List<Appointment> getAppointments(Long ordinationId) {
        return appointmentRepository.findByOrdinationIdAndStatusNotOrderByStartDateTime(ordinationId, AppointmentStatus.CANCELED);
    }

    @Override
    public List<Appointment> getDoctorAppointments(Long doctorId) {
        return appointmentRepository.findByDoctorsIdAndStatusNot(doctorId, AppointmentStatus.CANCELED);
    }

    @Override
    public List<Appointment> getNurseAppointments(Long nurseId) {
        return appointmentRepository.findByNurseIdAndStatusNot(nurseId, AppointmentStatus.CANCELED);
    }

    @Override
    public Appointment getAppointment(Long id) {
        try {
            return appointmentRepository.getByIdAndStatusNot(id, AppointmentStatus.CANCELED);
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Appointment> getCanceledAppointments() {
        return appointmentRepository.findByStatus(AppointmentStatus.CANCELED);
    }

    @Override
    public List<Appointment> getAwaitingApprovalAppointments() {
        return appointmentRepository.findByStatus(AppointmentStatus.AWAITING_APPROVAL);
    }

    @Override
    public List<Appointment> getPredefinedAwailableAppointments() {
        return appointmentRepository.findByStatus(AppointmentStatus.PREDEF_AVAILABLE);
    }

    @Override
    public List<Appointment> getPredefinedBookedAppointments() {
        return appointmentRepository.findByStatus(AppointmentStatus.PREDEF_BOOKED);
    }

    @Override
    public Appointment getOngoingAppointment(Long patientId, Long doctorId, LocalDateTime examinationStartTime) {
        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.PREDEF_BOOKED);
        statuses.add(AppointmentStatus.APPROVED);
        return appointmentRepository.findByPatientIdAndDoctorsIdAndDoctorsUserStatusAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanAndStatusIn(
                patientId, doctorId, UserStatus.ACTIVE, examinationStartTime, examinationStartTime, statuses);
    }

    @Override
    public Appointment assignOrdination(Appointment appointment, Ordination ordination, Nurse nurse) {
        appointment.setOrdination(ordination);
        appointment.setStatus(AppointmentStatus.APPROVED);
        if (nurse != null){
            appointment.setNurse(nurse);
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment cancelAppointment(Doctor doctor, Long appointmentId) {
        Appointment appointment = getAppointment(appointmentId);
        if (appointment == null){
            return null;
        }

        boolean foundDoctor = false;
        for (Doctor dr : appointment.getDoctors()){
            if (dr.getId().equals(doctor.getId())){
                foundDoctor = true;
                continue;
            }
        }

        if(!foundDoctor){
            return null;
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime canCancel = appointment.getStartDateTime().minusHours(24);
        if (now.isAfter(canCancel)){
            return null;
        }

        appointment.setStatus(AppointmentStatus.CANCELED);
        appointment.setDoctors(new HashSet<>());
        Nurse nurse = appointment.getNurse();
        appointment.setNurse(null);

        sendMail(appointment, appointment.getPatient(), doctor, nurse);
        return appointmentRepository.save(appointment);
    }

    @Override
    public void sendMail(Appointment appointment, Patient patient, Doctor doctor, Nurse nurse) {
        if (nurse == null || patient == null){
            return;
        }

        String subject = "Appointment notice: Your appointment has been canceled";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(doctor.getFirstName());
        stringBuilder.append(" ");
        stringBuilder.append(doctor.getLastName());
        stringBuilder.append("has canceled the appointment scheduled for ");
        stringBuilder.append(appointment.getStartDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")));

        String message = stringBuilder.toString();

        emailService.sendEmail(patient.getUsername(), subject, message);
        emailService.sendEmail(nurse.getUsername(), subject, message);
        if (appointment.getPrice().getAppointmentEnum().equals(AppointmentEnum.OPERATION)){
            for (Doctor dr : appointment.getDoctors()){
                if (dr.getId() != doctor.getId()){
                    emailService.sendEmail(dr.getUsername(), subject, message);
                }
            }
        }
    }
}
