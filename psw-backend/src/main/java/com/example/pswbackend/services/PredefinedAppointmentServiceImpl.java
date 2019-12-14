package com.example.pswbackend.services;
import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.repositories.PredefinedAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PredefinedAppointmentServiceImpl implements PredefinedAppointmentService{

    @Autowired
    private PredefinedAppointmentRepository predAppointRepo;

    @Override
    public Appointment findById(Long id){ return predAppointRepo.findOneById(id); }

    @Override
    public List<Appointment> findAll(){ return predAppointRepo.findAll(); }

    @Override
    public List<Appointment> findByClinicId(Long id){ return predAppointRepo.findByClinicId(id); }

    @Override
    public Appointment schedulePredefinedAppointment(Patient patient, Appointment appointment ){

            Set<Appointment> appointments= patient.getAppointments();
            appointments.add(appointment);
            patient.setAppointments(appointments);

            appointment.setStatus(AppointmentStatus.PREDEF_BOOKED);

            return appointment;

    }


}