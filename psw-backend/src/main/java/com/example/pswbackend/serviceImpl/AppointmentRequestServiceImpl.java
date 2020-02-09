package com.example.pswbackend.ServiceImpl;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.AppointmentRequestDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.repositories.*;
import com.example.pswbackend.services.AppointmentRequestService;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    ClinicAdminRepository clinicAdminRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public boolean saveRequest(AppointmentDoctorDTO dto, Clinic c) {

        Doctor doctor = doctorRepository.findOneById(Long.parseLong(dto.getDoctor()));
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


        AppointmentRequest ar = appointmentRequestRepository.findOneById(id);
        AppointmentPrice price = appointmentPriceRepository.findByAppointmentTypeIdAndAppointmentEnum(ar.getDoctor().getSpecialization().getId(), ar.getType());
        AppointmentRequestDTO dto = new AppointmentRequestDTO(ar.getId(),ar.getType().name(),ar.getStartDateTime(),ar.getEndDateTime(),ar.getDoctor().getFirstName(),ar.getDoctor().getLastName(),ar.getDoctor().getId());
        Patient p = patientRepo.findOneById(ar.getPatientId());
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
        for (AppointmentRequest ar : appointmentRequests) {
            AppointmentPrice price = appointmentPriceRepository.findByAppointmentTypeIdAndAppointmentEnum(ar.getDoctor().getSpecialization().getId(), ar.getType());
            AppointmentRequestDTO dto = new AppointmentRequestDTO(ar.getId(), ar.getType().name(), ar.getStartDateTime(), ar.getEndDateTime(), ar.getDoctor().getFirstName(), ar.getDoctor().getLastName(), ar.getDoctor().getId());
            Patient p = patientRepo.findOneById(ar.getPatientId());
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
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean sendRequest(AppointmentRequestDTO dto){

        if(dto.equals(null)){
            return false;
        }

        Patient patient = patientRepository.findOneById(dto.getPatientId());
        Doctor doctor = doctorRepository.findById(dto.getDoctorsId()).get();
        List<ClinicAdmin> clinicAdminList = clinicAdminRepository.findByClinicId(dto.getClinicId());
        ClinicAdmin ca = clinicAdminList.get(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time =  LocalDateTime.parse(dto.getStartTime(), formatter);
        AppointmentRequest appR= new AppointmentRequest(time, time.plusMinutes(40), doctor, ca.getClinic() ,dto.getAppointmentType(), dto.getPatientId());

        appointmentRequestRepository.save(appR);

        String subject = "Appointment notice: New request received";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(patient.getFirstName());
        stringBuilder.append(" ");
        stringBuilder.append(patient.getLastName());
        stringBuilder.append("has requested for examination.");
        stringBuilder.append(" Doctor:");
        stringBuilder.append(doctor.getFirstName());
        stringBuilder.append(" ");
        stringBuilder.append(doctor.getLastName());
        stringBuilder.append(". Time:");
        stringBuilder.append(time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        String message = stringBuilder.toString();

        emailService.sendEmail(ca.getUsername(), subject, message);

        return true;
    }
}
