package com.example.pswbackend.ServiceImpl;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.ClinicAdminDTO;
import com.example.pswbackend.dto.QuickReservationDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.enums.UserStatus;
import com.example.pswbackend.repositories.*;
import com.example.pswbackend.services.AppointmentRequestService;
import com.example.pswbackend.services.AppointmentService;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ClinicAdminServiceImpl implements ClinicAdminService {

    @Autowired
    ClinicAdminRepository clinicAdminRepository;

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    OrdinationRepository ordinationRepository;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentRequestService appointmentRequestService;

    @Autowired
    EmailService emailService;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public ClinicAdminDTO findByName(String name) {
        ClinicAdminDTO clinicAdminDTO = clinicAdminRepository.findByEmail(name);
        if (clinicAdminDTO == null) {
            return null;
        }

        return new ClinicAdminDTO(clinicAdminDTO.getEmail(), clinicAdminDTO.getPassword(), clinicAdminDTO.getFirstName(), clinicAdminDTO.getLastName(), clinicAdminDTO.getPhoneNumber(), clinicAdminDTO.getAddress(), clinicAdminDTO.getCity(), clinicAdminDTO.getCountry(), clinicAdminDTO.getClinicId());
    }

    @Override
    public ClinicAdmin register(ClinicAdminDTO clinicAdminDTO) {

        ClinicAdmin clinicAdmin = new ClinicAdmin();
        clinicAdmin.setFirstName(clinicAdminDTO.getFirstName());
        clinicAdmin.setLastName(clinicAdminDTO.getLastName());
        clinicAdmin.setPhoneNumber(clinicAdminDTO.getPhoneNumber());
        clinicAdmin.setEmail(clinicAdminDTO.getEmail());
        clinicAdmin.setAddress(clinicAdminDTO.getAddress());
        clinicAdmin.setCity(clinicAdminDTO.getCity());
        clinicAdmin.setCountry(clinicAdminDTO.getCountry());
        clinicAdmin.setPassword(clinicAdminDTO.getPassword());
        clinicAdmin.setClinic(clinicRepository.findOneById(clinicAdminDTO.getClinicId()));
        clinicAdmin.setUserStatus(UserStatus.NEVER_LOGGED_IN);
        if (clinicAdminRepository.findByEmail(clinicAdmin.getUsername()) != null){
            System.out.println("Vec postoji");
            return null;
        }

        String s = "You have been registered as an Admin of %s" + clinicAdmin.getClinic().getName() + " clinic! You can now log in to the Clinical Centre System";
        //emailService.sendEmail(clinicAdmin.getEmail(), "Registration Request Response", s);

        return clinicAdminRepository.save(clinicAdmin);

    }

    //TODO provera mogucnosti zakazivanja
    @Override
    public boolean receiveAppointmentRequest(AppointmentDoctorDTO dto) {

        ClinicAdmin ca = clinicAdminRepository.findAll().get(0);
        appointmentRequestService.saveRequest(dto, ca);

        return true;
    }

    //TODO
    @Override
    public List<ClinicAdmin> findAll() {
        return null;

    }

    @Override
    public Appointment createQuickReservation(QuickReservationDTO dto){

        // provere..............

        Appointment predefinedAppointment = new Appointment();

        AppointmentEnum appType;
        if (dto.getType() == "0"){
            appType = AppointmentEnum.EXAMINATION;
        } else {
            appType = AppointmentEnum.OPERATION;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(dto.getStartDateTime(), formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(dto.getEndDateTime(), formatter);

        AppointmentPrice price = new AppointmentPrice(appType, dto.getPrice());

        predefinedAppointment.setStartDateTime(startDateTime);
        predefinedAppointment.setEndDateTime(endDateTime);
        predefinedAppointment.setOrdination(ordinationRepository.findById(Long.parseLong(dto.getOrdination())).get());
        predefinedAppointment.setClinicAdmin(clinicAdminRepository.findById(Long.parseLong(dto.getClinicAdmin())).get());
        predefinedAppointment.setClinic(clinicAdminRepository.findById(Long.parseLong(dto.getClinicAdmin())).get().getClinic());
        //predefinedAppointment.setDoctor(doctorRepository.findById(Long.parseLong(dto.getDoctor())).get());
        predefinedAppointment.setPrice(price);
        predefinedAppointment.setStatus(AppointmentStatus.PREDEF_AVAILABLE);

        clinicAdminRepository.findById(Long.parseLong(dto.getClinicAdmin())).get().getPredefinedAppointments().add(predefinedAppointment);
        appointmentRepository.save(predefinedAppointment);

        return predefinedAppointment;
    }

    @Override
    public ClinicAdmin getLoggedInClinicAdmin() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        try {
            Account account = accountRepository.findByEmail(currentUser.getName());
            ClinicAdmin clinicAdmin = (ClinicAdmin) account;
            if (clinicAdmin != null) {
                return clinicAdmin;
            }
        } catch (UsernameNotFoundException ex) {
            return null;
        }
        return null;
    }
}
