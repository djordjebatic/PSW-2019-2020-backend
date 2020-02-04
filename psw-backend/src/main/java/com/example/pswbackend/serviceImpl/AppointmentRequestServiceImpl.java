package com.example.pswbackend.ServiceImpl;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.AppointmentRequestDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.repositories.AppointmentPriceRepository;
import com.example.pswbackend.repositories.AppointmentRequestRepository;
import com.example.pswbackend.repositories.DoctorRepository;
import com.example.pswbackend.repositories.PatientRepository;
import com.example.pswbackend.services.AppointmentRequestService;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentRequestServiceImpl implements AppointmentRequestService {

    @Autowired
    AppointmentRequestRepository appointmentRequestRepository;

    @Autowired
    AppointmentPriceRepository appointmentPriceRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ClinicAdminService clinicAdminService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientRepository patientRepo;

    @Override
    public boolean saveRequest(AppointmentDoctorDTO dto, Clinic c) {

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

        AppointmentRequest ar = new AppointmentRequest(startDateTime, endDateTime, doctor, c, appType, dto.getPatient());
        appointmentRequestRepository.save(ar);

        return true;
    }

    @Override
    public AppointmentRequestDTO getById(Long id) {

        AppointmentRequest ar = appointmentRequestRepository.findById(id).get();
        AppointmentPrice price = appointmentPriceRepository.findByAppointmentTypeIdAndAppointmentEnum(ar.getDoctor().getSpecialization().getId(), ar.getType());
        AppointmentRequestDTO dto = new AppointmentRequestDTO(ar.getId(),ar.getType().name(),ar.getStartDateTime(),ar.getEndDateTime(),ar.getDoctor().getFirstName(),ar.getDoctor().getLastName(),ar.getDoctor().getId());
        Patient p = patientRepo.findById(ar.getPatientId());
        dto.setTypeSpec(price.getAppointmentType().getName());
        dto.setPrice(price.getPrice());
        dto.setPriceId(price.getId());
        dto.setPatientId(p.getId());
        dto.setPatientFN(p.getFirstName());
        dto.setPatientLN(p.getLastName());
        return dto;
    }

    @Override
    public List<AppointmentRequestDTO> getClinicAppReqCA() {
        ClinicAdmin ca = clinicAdminService.getLoggedInClinicAdmin();
        if (ca == null){
            return null;
        }

        Clinic c = ca.getClinic();

        if (c == null){
            return null;
        }
        List<AppointmentRequestDTO> dtoList = new ArrayList<>();
        List<AppointmentRequest> appointmentRequests = c.getAppointmentRequests();
        for (AppointmentRequest ar:appointmentRequests) {
            AppointmentPrice price = appointmentPriceRepository.findByAppointmentTypeIdAndAppointmentEnum(ar.getDoctor().getSpecialization().getId(), ar.getType());
            AppointmentRequestDTO dto = new AppointmentRequestDTO(ar.getId(),ar.getType().name(),ar.getStartDateTime(),ar.getEndDateTime(),ar.getDoctor().getFirstName(),ar.getDoctor().getLastName(),ar.getDoctor().getId());
            Patient p = patientRepo.findById(ar.getPatientId());
            dto.setTypeSpec(price.getAppointmentType().getName());
            dto.setPrice(price.getPrice());
            dto.setPriceId(price.getId());
            dto.setPatientId(p.getId());
            dto.setPatientFN(p.getFirstName());
            dto.setPatientLN(p.getLastName());
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public List<AppointmentRequestDTO> getClinicAppReqDoc() {
        Doctor d = doctorService.getLoggedInDoctor();

        if (d == null) {
            return null;
        }

        Clinic c = d.getClinic();

        if (c == null) {
            return null;
        }

        List<AppointmentRequestDTO> dtoList = new ArrayList<>();
        List<AppointmentRequest> appointmentRequests = c.getAppointmentRequests();
        for (AppointmentRequest ar:appointmentRequests) {
            AppointmentPrice price = appointmentPriceRepository.findByAppointmentTypeIdAndAppointmentEnum(ar.getDoctor().getSpecialization().getId(), ar.getType());
            AppointmentRequestDTO dto = new AppointmentRequestDTO(ar.getId(),ar.getType().name(),ar.getStartDateTime(),ar.getEndDateTime(),ar.getDoctor().getFirstName(),ar.getDoctor().getLastName(),ar.getDoctor().getId());
            Patient p = patientRepo.findById(ar.getPatientId());
            dto.setTypeSpec(price.getAppointmentType().getName());
            dto.setPrice(price.getPrice());
            dto.setPriceId(price.getId());
            dto.setPatientId(p.getId());
            dto.setPatientFN(p.getFirstName());
            dto.setPatientLN(p.getLastName());
            dtoList.add(dto);
        }

        return dtoList;
    }
}
