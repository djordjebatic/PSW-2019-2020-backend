package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.PredefinedAppointmentDTO;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.repositories.AppointmentRepository;
import com.example.pswbackend.repositories.DoctorRepository;
import com.example.pswbackend.repositories.PatientRepository;
import com.example.pswbackend.services.EmailService;
import com.example.pswbackend.services.PredefinedAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pswbackend.repositories.DoctorRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PredefinedAppointmentServiceImpl implements PredefinedAppointmentService {

    @Autowired
    AppointmentRepository predAppointRepo;

    @Autowired
    EmailService emailService;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Appointment findById(Long id){ return predAppointRepo.findOneById(id); }

    @Override
    public List<Appointment> findAll(){ return predAppointRepo.findAll(); }

    @Override
    public List<PredefinedAppointmentDTO> findPredefinedByClinicId(long id){

        return convertToDTO(predAppointRepo.findByClinicIdAndStatus(id, AppointmentStatus.PREDEF_AVAILABLE));

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public PredefinedAppointmentDTO schedulePredefinedAppointment(Patient patient, Appointment appointment ){

            Set<Appointment> appointments= patient.getAppointments();
            appointments.add(appointment);
            patient.setAppointments(appointments);
            patientRepository.save(patient);
            appointment.setStatus(AppointmentStatus.PREDEF_BOOKED);
            appointment.setPatient(patient);
            predAppointRepo.save(appointment);

        String s = "Successfully scheduled appointment. ";
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append("Scheduled for: ");
        sb.append(appointment.getStartDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")));
        sb.append("in ordination number: ");
        sb.append(appointment.getOrdination());
        sb.append("Price: ");
        sb.append(appointment.getPrice().getPrice());
        String ret= sb.toString();

        emailService.sendEmail(patient.getUsername(), "Scheduled appointment approval", ret);


            return new PredefinedAppointmentDTO(appointment);

    }

    public List<PredefinedAppointmentDTO> convertToDTO(List<Appointment> appointments) {

        if (appointments.isEmpty()) {
            return new ArrayList<>();
        }
        List<PredefinedAppointmentDTO> predefinedAppointmentsDTO = new ArrayList<>();
        for (Appointment appointment : appointments) {

            predefinedAppointmentsDTO.add(new PredefinedAppointmentDTO(appointment));
        }

        return predefinedAppointmentsDTO;
    }

}
