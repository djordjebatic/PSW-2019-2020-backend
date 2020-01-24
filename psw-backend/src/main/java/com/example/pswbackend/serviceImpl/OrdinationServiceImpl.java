package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.NewOrdinationDTO;
import com.example.pswbackend.dto.OrdinationAssignDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.repositories.AppointmentRepository;
import com.example.pswbackend.repositories.DoctorRepository;
import com.example.pswbackend.repositories.OrdinationRepository;
import com.example.pswbackend.services.AppointmentService;
import com.example.pswbackend.services.ClinicService;
import com.example.pswbackend.services.EmailService;
import com.example.pswbackend.services.OrdinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Transactional
@Service
public class OrdinationServiceImpl implements OrdinationService {

    @Autowired
    OrdinationRepository ordinationRepository;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    ClinicService clinicService;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    EmailService emailService;

    @Override
    public Ordination findById(long id) {
        return ordinationRepository.findById(id).get();
    }

    @Override
    public List<Ordination> findAll() {
        return ordinationRepository.findAll();
    }

    @Override
    public List<OrdinationAssignDTO> findAllOrdinationsInClinic(Clinic clinic) {
        return convertToDTO(ordinationRepository.findByClinicId(clinic.getId()));
    }

    @Override
    public Appointment assignOrdinationForOperation(Long appointmentId, Long ordinationId, Set<Doctor> doctors) {
        Appointment appointment = appointmentService.getAppointment(appointmentId);
        Ordination ordination = ordinationRepository.findOneById(ordinationId);

        if (appointment == null || ordination == null || appointment.getPrice().getAppointmentEnum().equals(AppointmentEnum.EXAMINATION)) {
            System.out.println("--1--");
            return null;
        }
        if (appointment.getStartDateTime().isBefore(LocalDateTime.now())) {
            System.out.println("--2--");
            return null;
        }

        if (!isOrdinationAvailable(ordination, appointment.getStartDateTime(), appointment.getEndDateTime())) {
            System.out.println("--3--");
            return null;
        }

        //All doctors must be available
        for (Doctor doctor : doctors) {
            if (!isDoctorAvailable(doctor, appointment.getStartDateTime(), appointment.getEndDateTime())) {
                System.out.println("--4--");
                return null;
            }
        }
        appointmentService.assignOperationOrdination(appointment, ordination, doctors);
        return appointment;
    }

    @Override
    public void assignOrdinationAutomatically() {
        System.out.println("++++++++++USAO U METODU+++++++++++++++++++");
        List<Appointment> appointmentsAwaitingApproval = appointmentService.getAwaitingApprovalAppointments();
        for (Appointment appointment : appointmentsAwaitingApproval) {
            System.out.println("++++++++++Ima ih koji cekaju+++++++++++++++++++    " + appointment.getId());

            List<Ordination> availableOrdinations = getAvailableOrdinations(appointment);
            List<Doctor> availableDoctors = getAvailableDoctors(appointment);

            if (appointment.getPrice().getAppointmentEnum().equals(AppointmentEnum.OPERATION)) {
                System.out.println("operacija");
                if (!availableOrdinations.isEmpty()) {
                    System.out.println("nije prazna ordinacija");
                    if (!availableDoctors.isEmpty()) {
                        System.out.println("Slobodan doktor");
                        Ordination ordination = availableOrdinations.get(new Random().nextInt(availableOrdinations.size()));

                        Set<Doctor> doctorSet = new HashSet<>();
                        Doctor doctor = availableDoctors.get(new Random().nextInt(availableDoctors.size()));
                        doctorSet.add(doctor);

                        assignOrdinationForOperation(appointment.getId(), ordination.getId(), doctorSet);
                    }
                } else {
                    System.out.println("sledeci dan");
                    assignNextDay(appointment, availableDoctors);
                }
            } else {
                //TODO EXAMINATION
            }
        }
    }

    public Ordination assignNextDay(Appointment appointment, List<Doctor> availableDoctors) {
        //search from 8 am to 8 pm
        Ordination ordination = null;
        long duration = Duration.between(appointment.getStartDateTime(), appointment.getEndDateTime()).toMillis() / 1000;

        LocalDateTime start = appointment.getStartDateTime().toLocalDate().atStartOfDay().plusHours(8);
        LocalDateTime end = start.plusSeconds(duration);
        LocalDateTime end_search = appointment.getStartDateTime().toLocalDate().atStartOfDay().plusHours(20);

        List<Ordination> ordinations = ordinationRepository.findByClinicId(appointment.getId());

        for (Ordination o : ordinations) {
            if (isOrdinationAvailable(o, start, start.plusSeconds(duration))) {

                Set<Doctor> doctorSet = new HashSet<>();
                Doctor doctor = availableDoctors.get(new Random().nextInt(availableDoctors.size()));
                doctorSet.add(doctor);

                assignOrdinationForOperation(appointment.getId(), o.getId(), doctorSet);

                ordination = o;
            } else {
                while (end.isBefore(end_search)) {
                    start = end;
                    end = end.plusSeconds(duration);
                    if (isOrdinationAvailable(o, start, end)) {

                        Set<Doctor> doctorSet = new HashSet<>();
                        Doctor doctor = availableDoctors.get(new Random().nextInt(availableDoctors.size()));
                        doctorSet.add(doctor);

                        assignOrdinationForOperation(appointment.getId(), o.getId(), doctorSet);
                        ordination = o;
                    }
                }
            }
        }

        return ordination;
    }

    public List<Doctor> getAvailableDoctors(Appointment appointment){
        List<Doctor> doctors = doctorRepository.findByClinicId(appointment.getClinic().getId());
        List<Doctor> availableDoctors = new ArrayList<>();
        for (Doctor d : doctors){
            if (isDoctorAvailable(d, appointment.getStartDateTime(), appointment.getEndDateTime())){
                availableDoctors.add(d);
            }
        }
        return availableDoctors;
    }

    public List<Ordination> getAvailableOrdinations(Appointment appointment){
        List<Ordination> ordinations = ordinationRepository.findByClinicId(appointment.getId());
        List<Ordination> availableOrdinations = new ArrayList<>();

        for (Ordination o : ordinations){
            if (isOrdinationAvailable(o, appointment.getStartDateTime(), appointment.getEndDateTime())){
                availableOrdinations.add(o);
            }
        }

        return availableOrdinations;
    }

    public boolean isOrdinationAvailable(Ordination ordination, LocalDateTime start, LocalDateTime end) {
        List<Appointment> appointments = appointmentService.getOrdinationAppointmentsDuringTheDay(ordination.getId(), start);
        boolean available = false;
        if (appointments.isEmpty()){
            return true;
        }
        else {
            for (Appointment appointment : appointments) {
                    if (!checkTaken(appointment, start, end)) {
                        available = true;
                    }
            }
        }
        return available;
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

    private List<OrdinationAssignDTO> convertToDTO(List<Ordination> ordinations) {
        if (ordinations == null || ordinations.isEmpty()) {
            return new ArrayList<>();
        }
        List<OrdinationAssignDTO> ordinationAssignDTOS = new ArrayList<>();
        for (Ordination ordination : ordinations) {
            ordinationAssignDTOS.add(new OrdinationAssignDTO(ordination));
        }
        return ordinationAssignDTOS;
    }

    @Override
    public Ordination addNew(NewOrdinationDTO dto){

        Ordination o = new Ordination();
        AppointmentEnum app;
        if (Integer.parseInt(dto.getType()) == 0){
            app = AppointmentEnum.EXAMINATION;
            o.setType(app);
        } else if (Integer.parseInt(dto.getType()) == 1){
            app = AppointmentEnum.OPERATION;
            o.setType(app);
        }

        Clinic c = clinicService.findClinicById(dto.getClinicId());

        o.setNumber(dto.getNumber());
        o.setClinic(c);

        c.getOrdinations().add(o);

        return o;
    }

    public List<Ordination> findByClinicId(Long clinicId){
        return ordinationRepository.findByClinicId(clinicId);
    }
}
