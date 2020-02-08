package com.example.pswbackend.ServiceImpl;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.*;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.enums.PaidTimeOffStatus;
import com.example.pswbackend.enums.PaidTimeOffType;
import com.example.pswbackend.repositories.*;
import com.example.pswbackend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    @Autowired
    private PaidTimeOffDoctorRepository paidTimeOffDoctorRepository;

    @Autowired
    private ClinicAdminRepository clinicAdminRepo;


    @Override
    public boolean scheduleAppointment(AppointmentDoctorDTO dto) {

        Patient patient = patientRepo.findById(dto.getPatient());
        Doctor doctor = doctorRepo.findById(Long.parseLong(dto.getDoctor())).get();

        int type = Integer.parseInt(dto.getType());
        String stringType = type == 0 ? "Examination" : "Operation";

        // TODO dodati startDateTime endDateTime

        String message = "Hello, " + "Clinic Admin" +
                ".\nYou got a new request for scheduling an appointment.\n\nAppointment information: \n" +
                "-----------------------------------\n" +
                "     Doctor: " + doctor.getFirstName() + " " + doctor.getLastName() + "\n" +
                "     Patient: " + patient.getFirstName() + " " + patient.getLastName() + "\n" +
                "     Type: " + stringType + "\n" +
                "     Start: " + dto.getStartDateTime()+
                "\n     End: " + dto.getEndDateTime() +
                "\n-----------------------------------";

        clinicAdminService.receiveAppointmentRequest(dto);

        emailService.sendEmail("jelenadostic2@gmail.com", "Scheduling Appointment", message);
        List<ClinicAdmin> ca = clinicAdminRepo.findByClinicId(doctorRepo.findById(Long.parseLong(dto.getDoctor())).get().getClinic().getId());

        for (int i=0;i<ca.size();i++){
            emailService.sendEmail(ca.get(i).getUsername(), "Scheduling Appointment", message);
        }

        return true; //za sad uvek true
    }

    @Override
    public Doctor findById(long id) {
        return doctorRepo.findById(id).get();
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepo.findAll();
    }

    @Override
    public List<NewDoctorDTO> findByWorkingTime(LocalTime start, LocalTime end) {
        ClinicAdmin ca = clinicAdminService.getLoggedInClinicAdmin();
        if (ca == null){
            return null;
        }

        List<Doctor> doctors = doctorRepo.findByWorkTimeStartGreaterThanEqualAndWorkTimeEndLowerThan(start, end, ca.getClinic().getId());
        List<NewDoctorDTO> dtoDoctors = new ArrayList<>();
        for (Doctor d:doctors) {
            dtoDoctors.add(new NewDoctorDTO(d.getId(),d.getFirstName(),d.getLastName(),d.getUsername(),d.getPhoneNumber(),d.getCountry(),d.getCity(),d.getAddress(),d.getClinic().getId(),d.getWorkTimeStart(),d.getWorkTimeEnd(),d.getSpecialization().getName()));
        }
        return dtoDoctors;
    }

    @Override
    public List<Doctor> findByClinicId(long id) { return doctorRepo.findByClinicId(id);}

    @Override
    public List<NewDoctorDTO> findClinicDoctors(Long id) {

        List<Doctor> doctors = doctorRepo.findByClinicId(id);
        List<NewDoctorDTO> dtoDocs = new ArrayList<>();
        for (Doctor d:doctors) {
            dtoDocs.add(new NewDoctorDTO(d.getId(),d.getFirstName(),d.getLastName(),d.getUsername(),d.getPhoneNumber(),d.getCountry(),d.getCity(),d.getAddress(),d.getClinic().getId(),d.getWorkTimeStart(),d.getWorkTimeEnd(),d.getSpecialization().getName()));
        }

        return dtoDocs;
    }

    @Override
    public PaidTimeOffDoctor requestLeave(Long id, PaidTimeOffDoctorDTO dto) {
        Doctor doc = doctorRepo.findById(id).get();

        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.APPROVED);
        statuses.add(AppointmentStatus.PREDEF_BOOKED);

        List<Appointment> appointmens = appointmentRepository
                .findByDoctorIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanAndStatusIn(id,
                        dto.getStartDateTime(), dto.getEndDateTime(),
                        statuses);

        if (!appointmens.isEmpty()){
            return null;
        }

        PaidTimeOffDoctor paidTimeOffDoctor = new PaidTimeOffDoctor();

        paidTimeOffDoctor.setDoctor(doc);
        paidTimeOffDoctor.setComment(dto.getComment());
        paidTimeOffDoctor.setStartDateTime(dto.getStartDateTime());
        paidTimeOffDoctor.setEndDateTime(dto.getEndDateTime());

        if (dto.getPaidTimeOffType().equals(PaidTimeOffType.ANNUAL_LEAVE)){
            paidTimeOffDoctor.setPaidTimeOffType(PaidTimeOffType.ANNUAL_LEAVE);
        }
        else {
            paidTimeOffDoctor.setPaidTimeOffType(PaidTimeOffType.SICK_LEAVE);
        }

        paidTimeOffDoctor.setPaidTimeOffStatus(PaidTimeOffStatus.REQUESTED);

        return paidTimeOffDoctorRepository.save(paidTimeOffDoctor);
    }

    @Override
    public boolean alreadyRequestedLeave(Doctor dr) {
        if (dr.getPaidTimeOffDoctor() != null) {
            if (dr.getPaidTimeOffDoctor().getPaidTimeOffStatus().equals(PaidTimeOffStatus.REQUESTED)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean alreadyOnLeave(Doctor dr) {
        if (dr.getPaidTimeOffDoctor() != null) {
            if (dr.getPaidTimeOffDoctor().getPaidTimeOffStatus().equals(PaidTimeOffStatus.APPROVED)) {
                if (dr.getPaidTimeOffDoctor().getStartDateTime().isAfter(LocalDateTime.now()) &&
                        dr.getPaidTimeOffDoctor().getEndDateTime().isBefore(LocalDateTime.now())
                )
                    return true;
            }
        }
        return false;
    }

    @Override
    public List<Doctor> findByClinicIdAndSpecializationId(Appointment appointment) {

        Clinic clinic = appointment.getClinic();
        AppointmentType appointmentType = appointment.getPrice().getAppointmentType();

        System.out.println("CLINIC: " + clinic.getId());
        System.out.println("TYPE: " + appointmentType.getId());

        return doctorRepo.findByClinicIdAndSpecializationId(clinic.getId(), appointmentType.getId());
    }

    @Override
    public List<NewDoctorDTO> getAvailableDoctorsByDateAndTime(DateAndTimeDTO dto) {

        LocalDateTime startDateTime = dto.getStart();
        LocalDateTime endDateTime = dto.getEnd();
        LocalDate date = startDateTime.toLocalDate();
        LocalTime startTime = startDateTime.toLocalTime();
        LocalTime endTime = endDateTime.toLocalTime();

        List<Doctor> doctors = doctorRepo.findByClinicId(clinicAdminService.getLoggedInClinicAdmin().getClinic().getId()); // doktori te klinike
        List<PaidTimeOffDoctor> doctorsOnLeave = paidTimeOffDoctorRepository.findByPaidTimeOffStatus(PaidTimeOffStatus.APPROVED); //odobrena odsustva
        Set<Long> doctorsIds = new HashSet<>();

        for (PaidTimeOffDoctor ptod:doctorsOnLeave) {
            if(ptod.getStartDateTime().toLocalDate().isBefore(date) && ptod.getEndDateTime().toLocalDate().isAfter(date)){
                doctorsIds.add(ptod.getDoctor().getId());
            }
        }

        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.APPROVED);
        statuses.add(AppointmentStatus.PREDEF_BOOKED);

        List<NewDoctorDTO> availableDoctors = new ArrayList<>();
        for (Doctor d:doctors) {
            if (!doctorsIds.contains(d.getId())){  // doktori koji nisu odsutni
                if(d.getWorkTimeStart().isBefore(startTime) && d.getWorkTimeEnd().isAfter(endTime)){  // i koje rade u to vreme
                    if (appointmentRepository.findByDoctorsIdAndStatusIn(d.getId(),statuses).size() == 0){  // i koji nemaju zakazan termin u to vreme
                        availableDoctors.add(new NewDoctorDTO(d.getId(),d.getFirstName(),d.getLastName(),d.getUsername(),
                                d.getPhoneNumber(),d.getCountry(),d.getCity(),d.getAddress(),d.getClinic().getId(),d.getWorkTimeStart(),d.getWorkTimeEnd(),d.getSpecialization().getName()));
                    }
                }
            }
        }

        return availableDoctors;
    }

    @Override
    public Doctor getLoggedInDoctor() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        try {
            Account account = accountRepository.findByEmail(currentUser.getName());
            Doctor doctor = (Doctor)account;
            if (doctor != null) {
                return doctor;
            }
        } catch (UsernameNotFoundException ex) {
            return null;
        }
        return null;
    }

    @Override
    public Doctor addNew(NewDoctorDTO dto) {

        Clinic c = clinicService.findClinicById(dto.getClinicId());
        List<AppointmentType> types = c.getAppointmentTypes();
        String specId = dto.getSpecialization();
        AppointmentType type = appointmentTypeRepository.findOneById(Long.parseLong(specId));

        Doctor d = new Doctor();
        if (type != null){
            d.setSpecialization(type);
        }
        d.setFirstName(dto.getFirstName());
        d.setLastName(dto.getLastName());
        d.setEmail(dto.getUsername());
        d.setWorkTimeStart(dto.getWorkTimeStart());
        d.setWorkTimeEnd(dto.getWorkTimeEnd());
        d.setPassword(passwordEncoder.encode("doktor"));
        d.setPhoneNumber(dto.getPhoneNumber());
        d.setCity(dto.getCity());
        d.setCountry(dto.getCountry());
        d.setAddress(dto.getAddress());
        d.setClinic(c);

        List<Authority> authorities = new ArrayList<Authority>();
        Authority a = new Authority();
        a.setName("ROLE_DOCTOR");
        a.setId(2L);
        authorities.add(a);
        d.setAuthorities(authorities);

        String message = "Hello " + d.getFirstName() +
                ",\n\nWelcome to Clinic Center.\n You are added as a new doctor to the clinic '" + c.getName() + "'.\n\nYou can now log in with initial password: 'doktor'.";

        //TODO email service => javi doktoru sifru
        emailService.sendEmail(d.getUsername(), "Welcome doctor!",message);

        c.getDoctors().add(d);
        doctorRepo.save(d);

        return d;

    }


    @Override
    public Boolean deleteOneById(Long id) {
        try {
            doctorRepo.deleteOneById(id);
            return true;
        } catch (Exception e){
            return true; //exception jer delete ne vraca nista -> PSQLException: No results were returned by the query
        }
    }

    public List<ResultDoctorDTO> filterDoctors(FilterDoctorsDTO dto) {

        List<Doctor> doctorList = new ArrayList<Doctor>();
        List<ResultDoctorDTO> listDoctorDTO = new ArrayList<>();
        List<Doctor> clinicDoctors = doctorRepo.findByClinicId(Long.parseLong(dto.getClinicsId()));
        for(Doctor d : clinicDoctors){

            List<LocalDateTime> freeTerms = new ArrayList<>();
            List<String> free = new ArrayList<>();
            List<ResultAvailableDoctorTimeDTO> TIME= new ArrayList<>();
            if(d.getSpecialization().getName().equals(dto.getType())) {

                long duration = Duration.between(dto.getDate().atStartOfDay().plusHours(8), dto.getDate().atStartOfDay().plusHours(8).plusMinutes(40)).toMillis() / 1000;
                long duration2 = Duration.between(dto.getDate().atStartOfDay().plusHours(8), dto.getDate().atStartOfDay().plusHours(8).plusMinutes(45)).toMillis() / 1000;

                LocalDateTime start = dto.getDate().atStartOfDay().plusHours(8);

                if(d.getPaidTimeOffDoctor()!=null) {
                    if (d.getPaidTimeOffDoctor().getStartDateTime().isAfter(dto.getDate().atStartOfDay())
                            || d.getPaidTimeOffDoctor().getStartDateTime().isAfter(dto.getDate().atStartOfDay())) {

                        if (d.getWorkTimeStart().equals(LocalTime.of(8, 0))) {
                            for (int i = 0; i < 8; i++) {
                                LocalDateTime st = start.plusSeconds(i * duration2);

                                if (isDoctorAvailable(d, st, st.plusSeconds(duration))) {
                                    if (!doctorList.contains(d)) {
                                        doctorList.add(d);
                                    }
                                    free.add(st.format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy")));
                                    freeTerms.add(st);

                                    Long a = Long.parseLong(d.getId() + "" + i);
                                    ResultAvailableDoctorTimeDTO oneResult = new ResultAvailableDoctorTimeDTO(a, st.format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy")));
                                    TIME.add(oneResult);
                                }
                            }
                        }
                        if (d.getWorkTimeStart().equals(LocalTime.of(14, 0))) {
                            for (int i = 8; i < 16; i++) {
                                LocalDateTime st = start.plusSeconds(i * duration2);

                                if (isDoctorAvailable(d, st, st.plusSeconds(duration))) {
                                    if (!doctorList.contains(d)) {
                                        doctorList.add(d);
                                    }
                                    free.add(st.format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy")));
                                    freeTerms.add(st);

                                    Long a = Long.parseLong(d.getId() + "" + i);
                                    ResultAvailableDoctorTimeDTO oneResult = new ResultAvailableDoctorTimeDTO(a, st.format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy")));
                                    TIME.add(oneResult);
                                }
                            }
                        }
                    }
                }else{
                    if(d.getWorkTimeStart().equals(LocalTime.of(8,0))) {
                        for (int i = 0; i < 8; i++) {
                            LocalDateTime st = start.plusSeconds(i * duration2);

                            if (isDoctorAvailable(d, st, st.plusSeconds(duration))) {
                                if (!doctorList.contains(d)) {
                                    doctorList.add(d);
                                }
                                free.add(st.format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy")));
                                freeTerms.add(st);

                                Long a = Long.parseLong(d.getId() + "" + i);
                                ResultAvailableDoctorTimeDTO oneResult = new ResultAvailableDoctorTimeDTO(a, st.format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy")));
                                TIME.add(oneResult);
                            }
                        }
                    }

                    if(d.getWorkTimeStart().equals(LocalTime.of(14,0))) {
                        for (int i = 8; i < 16; i++) {
                            LocalDateTime st = start.plusSeconds(i * duration2);

                            if (isDoctorAvailable(d, st, st.plusSeconds(duration))) {
                                if (!doctorList.contains(d)) {
                                    doctorList.add(d);
                                }
                                free.add(st.format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy")));
                                freeTerms.add(st);

                                Long a = Long.parseLong(d.getId() + "" + i);
                                ResultAvailableDoctorTimeDTO oneResult = new ResultAvailableDoctorTimeDTO(a, st.format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy")));
                                TIME.add(oneResult);
                            }
                        }
                    }
                }
            }

            if(!freeTerms.isEmpty()) {
                int r= d.getStars()/d.getNum_votes();
                ResultDoctorDTO resultDTO = new ResultDoctorDTO(d.getId().toString(),d.getFirstName(), d.getLastName(), Integer.toString(r), freeTerms, free, TIME);
                listDoctorDTO.add(resultDTO);
            }
        }
        return listDoctorDTO;
    }

    public boolean isDoctorAvailable(Doctor doctor, LocalDateTime start, LocalDateTime end) {
        List<Appointment> appointments = appointmentService.getDoctorAppointmentsDuringTheDay(doctor.getId(), start);
        boolean available = false;
        if (appointments.isEmpty()){
            return true;
        }
        else{
            for (Appointment appointment : appointments) {
                if (!checkTaken(appointment, start, end)) {
                    available = true;
                }
            }
        }
        return available;
    }

    public boolean checkTaken(Appointment appointment, LocalDateTime start, LocalDateTime end){

        LocalDateTime appointment_start = appointment.getStartDateTime();
        LocalDateTime appointment_end = appointment.getEndDateTime();

        if (appointment_end.isAfter(end)){
            if (appointment_start.isBefore(end)){
                return true;
            }
        }
        if (appointment_start.isBefore(start)){
            if (appointment_end.isAfter(start)){
                return true;
            }
        }
        return false;
    }
}

