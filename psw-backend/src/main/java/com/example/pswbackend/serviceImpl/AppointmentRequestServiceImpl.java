package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.AppointmentRequest;
import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.repositories.AppointmentRequestRepository;
import com.example.pswbackend.repositories.DoctorRepository;
import com.example.pswbackend.services.AppointmentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentRequestServiceImpl implements AppointmentRequestService {

    @Autowired
    AppointmentRequestRepository appointmentRequestRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public boolean saveRequest(AppointmentDoctorDTO dto, ClinicAdmin ca) {

        Doctor doctor = doctorRepository.findById(Long.parseLong(dto.getDoctor())).get();
        AppointmentRequest ar = new AppointmentRequest(dto.getDate(), dto.getTime(), doctor, ca);
        appointmentRequestRepository.save(ar);

        return true;
    }
}
