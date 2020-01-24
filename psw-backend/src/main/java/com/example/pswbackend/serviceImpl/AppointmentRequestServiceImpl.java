package com.example.pswbackend.ServiceImpl;

import com.example.pswbackend.domain.AppointmentRequest;
import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.repositories.AppointmentRequestRepository;
import com.example.pswbackend.repositories.DoctorRepository;
import com.example.pswbackend.services.AppointmentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AppointmentRequestServiceImpl implements AppointmentRequestService {

    @Autowired
    AppointmentRequestRepository appointmentRequestRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public boolean saveRequest(AppointmentDoctorDTO dto, ClinicAdmin ca) {

        Doctor doctor = doctorRepository.findById(Long.parseLong(dto.getDoctor())).get();
        AppointmentEnum appType;
        if (Integer.parseInt(dto.getType()) == 0){
            appType = AppointmentEnum.EXAMINATION;
        } else {
            appType = AppointmentEnum.OPERATION;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(dto.getStartDateTime(), formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(dto.getEndDateTime(), formatter);

        AppointmentRequest ar = new AppointmentRequest(startDateTime, endDateTime, doctor, ca, appType, dto.getPatient());
        appointmentRequestRepository.save(ar);

        return true;
    }
}
