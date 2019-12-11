package com.example.pswbackend.services;
import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.repositories.ClinicRepository;
import com.example.pswbackend.repositories.PredefinedAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class PredefinedAppointmentServiceImpl implements PredefinedAppointmentService{

    @Autowired
    private PredefinedAppointmentRepository predAppointRepo;

    @Override
    public Appointment findById(Long id){

        return predAppointRepo.findOneById(id);
    }

    @Override
    public List<Appointment> findAll(){

        return predAppointRepo.findAll();
    }




}
