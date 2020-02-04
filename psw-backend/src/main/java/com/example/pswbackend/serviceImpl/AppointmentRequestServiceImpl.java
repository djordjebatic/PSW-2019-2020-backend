package com.example.pswbackend.ServiceImpl;

import com.example.pswbackend.domain.AppointmentRequest;
import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.AppointmentRequestDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.repositories.AppointmentRequestRepository;
import com.example.pswbackend.repositories.ClinicAdminRepository;
import com.example.pswbackend.repositories.DoctorRepository;
import com.example.pswbackend.repositories.PatientRepository;
import com.example.pswbackend.services.AppointmentRequestService;
import com.example.pswbackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AppointmentRequestServiceImpl implements AppointmentRequestService {

    @Autowired
    AppointmentRequestRepository appointmentRequestRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ClinicAdminRepository clinicAdminRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    PatientRepository patientRepository;

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

    @Override
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
        AppointmentRequest appR= new AppointmentRequest(time, time.plusMinutes(40), doctor, ca ,dto.getAppointmentType(), dto.getPatientId());

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
