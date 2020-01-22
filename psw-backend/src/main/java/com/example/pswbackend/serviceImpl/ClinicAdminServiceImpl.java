package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.ClinicAdminDTO;
import com.example.pswbackend.dto.QuickReservationDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.UserStatus;
import com.example.pswbackend.repositories.*;
import com.example.pswbackend.services.AppointmentRequestService;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        return new ClinicAdminDTO(clinicAdminDTO.getEmail(), clinicAdminDTO.getFirstName(), clinicAdminDTO.getLastName(), clinicAdminDTO.getPhoneNumber(), clinicAdminDTO.getAddress(), clinicAdminDTO.getCity(), clinicAdminDTO.getCountry(), clinicAdminDTO.getClinicId());
    }

    @Override
    public ClinicAdmin register(ClinicAdminDTO clinicAdminDTO) {

        if (accountRepository.findByEmail(clinicAdminDTO.getEmail()) != null){
            return null;
        }

        ClinicAdmin clinicAdmin = new ClinicAdmin();
        clinicAdmin.setFirstName(clinicAdminDTO.getFirstName());
        clinicAdmin.setLastName(clinicAdminDTO.getLastName());
        clinicAdmin.setPhoneNumber(clinicAdminDTO.getPhoneNumber());
        clinicAdmin.setEmail(clinicAdminDTO.getEmail());
        clinicAdmin.setAddress(clinicAdminDTO.getAddress());
        clinicAdmin.setCity(clinicAdminDTO.getCity());
        clinicAdmin.setCountry(clinicAdminDTO.getCountry());
        clinicAdmin.setPassword("$2y$12$4zrqOojpixOe/ogFw1xyyuQuIvFqrzbj0IohYtshqqy1P5rS6kdbq");
        clinicAdmin.setClinic(clinicRepository.findOneById(clinicAdminDTO.getClinicId()));
        clinicAdmin.setUserStatus(UserStatus.NEVER_LOGGED_IN);
        List<Authority> authorities = new ArrayList<>();
        Authority a = new Authority();
        a.setName("ROLE_CLINIC_ADMIN");
        a.setId(4L);
        authorities.add(a);
        clinicAdmin.setAuthorities(authorities);

        String s = "You have been registered as an Admin of %s" + clinicAdmin.getClinic().getName() + " clinic!" +
                " You can now log in to the Clinical Centre System! To log in use the default password: \"admin\". " +
                "You will have to change this password after your first log in.";
        emailService.sendEmail(clinicAdmin.getUsername(), "Registration Request Response", s);

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

        System.out.println("-------------------- ORDINATION: " + dto.getDoctor());

        //predefinedAppointment.setDate(dto.getDate());
        //predefinedAppointment.setTime(dto.getTime());
        predefinedAppointment.setOrdination(ordinationRepository.findById(Long.parseLong(dto.getOrdination())).get());
        //predefinedAppointment.setDoctor(doctorRepository.findById(Long.parseLong(dto.getDoctor())).get());
        //predefinedAppointment.setDuration(dto.getDuration());
        predefinedAppointment.getPrice().setPrice(dto.getPrice());
        predefinedAppointment.getPrice().setAppointmentEnum(appType);

        //TODO dodati u listu predefinisanih appointmenta
        /// dto.getClinicAdmin().getPredefinedAppointmetns().push/add/saveNew...

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
