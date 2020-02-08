package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.*;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.enums.UserStatus;
import com.example.pswbackend.repositories.*;
import com.example.pswbackend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentRequestRepository appointmentRequestRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Autowired
    OrdinationRepository ordinationRepo;

    @Autowired
    ClinicAdminService clinicAdminService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private OrdinationService ordinationService;

    @Autowired
    private PatientRepository patientService;

    @Autowired
    private AppointmentPriceRepository appointmentPriceService;

    @Autowired
    NurseRepository nurseRepository;


    @Override
    public List<AppointmentCalendarDTO> getDoctorAppointments(Long doctorId) {

        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.APPROVED);
        statuses.add(AppointmentStatus.PREDEF_BOOKED);

        return convertToDTO(appointmentRepository.findByDoctorsIdAndStatusIn(doctorId, statuses));
    }

    @Override
    public List<AppointmentCalendarDTO> getOrdinationAppointments(Long ordinationId){

        return convertToDTO(appointmentRepository.findByOrdinationId(ordinationId));
    }

    @Override
    public List<AppointmentCalendarDTO> getNurseAppointments(Long nurseId) {
        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.APPROVED);
        statuses.add(AppointmentStatus.PREDEF_BOOKED);

        return convertToDTO(appointmentRepository.findByNurseIdAndStatusIn(nurseId, statuses));
    }

    @Override
    public Appointment getAppointment(Long id) {
            return appointmentRepository.getByIdAndStatusNot(id, AppointmentStatus.CANCELED);
    }

    @Override
    public List<Appointment> getAwaitingApprovalAppointments() {
        return appointmentRepository.findByStatus(AppointmentStatus.AWAITING_APPROVAL);
    }

    @Override
    public Appointment getOngoingAppointment(Long patientId, Long doctorId, LocalDateTime examinationStartTime) {
        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.PREDEF_BOOKED);
        statuses.add(AppointmentStatus.APPROVED);
        return appointmentRepository.findByPatientIdAndDoctorsIdAndDoctorsUserStatusAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanAndStatusIn(
                patientId, doctorId, UserStatus.ACTIVE, examinationStartTime, examinationStartTime, statuses);
    }

    @Override
    public List<Appointment> getOrdinationAppointmentsDuringTheDay(Long ordinationId, LocalDateTime day) {

        LocalDate date = day.toLocalDate();

        LocalDateTime start = LocalDateTime.of(date, LocalTime.of(0, 0));
        LocalDateTime end = LocalDateTime.of(date, LocalTime.of(23, 59, 59));

        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.PREDEF_BOOKED);
        statuses.add(AppointmentStatus.APPROVED);

        return appointmentRepository.findByOrdinationIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndStatusIn(ordinationId, start, end, statuses);
    }

    @Override
    public List<Appointment> getDoctorAppointmentsDuringTheDay(Long doctorId, LocalDateTime day) {
        LocalDate date = day.toLocalDate();

        LocalDateTime start = LocalDateTime.of(date, LocalTime.of(0, 0));
        LocalDateTime end = LocalDateTime.of(date, LocalTime.of(23, 59, 59));

        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.PREDEF_BOOKED);
        statuses.add(AppointmentStatus.APPROVED);

        return appointmentRepository.findByDoctorsIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndStatusIn(doctorId, start, end, statuses);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Appointment assignOperationOrdination(Appointment appointment, Ordination ordination, Set<Doctor> doctors) throws PessimisticLockingFailureException {

        if (ordination == null) {
            throw new ValidationException("Ordination value is null");
        }
        if (appointment == null){
            throw new ValidationException("Appointment value is null");
        }
        if (doctors.isEmpty()){
            throw new ValidationException("You must assign at least one doctor before assigning the ordination");
        }

        appointment.setOrdination(ordination);
        appointment.setDoctors(doctors);
        appointment.setStatus(AppointmentStatus.APPROVED);
        appointmentRepository.save(appointment);
        sendOperationMail(appointment);
        return appointment;
    }

    @Override
    public void sendCancelationMail(Appointment appointment, Patient patient, Doctor doctor, Nurse nurse) {
        if (nurse == null || patient == null){
            return;
        }

        String subject = "Appointment notice: Your appointment has been canceled";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(doctor.getFirstName());
        stringBuilder.append(" ");
        stringBuilder.append(doctor.getLastName());
        stringBuilder.append("has canceled the appointment scheduled for ");
        stringBuilder.append(appointment.getStartDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")));

        String message = stringBuilder.toString();

        emailService.sendEmail(patient.getUsername(), subject, message);
        emailService.sendEmail(nurse.getUsername(), subject, message);
        if (appointment.getPrice().getAppointmentEnum().equals(AppointmentEnum.OPERATION)){
            for (Doctor dr : appointment.getDoctors()){
                if (dr.getId() != doctor.getId()){
                    emailService.sendEmail(dr.getUsername(), subject, message);
                }
            }
        }
    }

    public List<AppointmentCalendarDTO> convertToDTO(List<Appointment> appointments){

        if (appointments.isEmpty()) {
            return new ArrayList<>();
        }
        List<AppointmentCalendarDTO> appointmentCalendarDTOS = new ArrayList<>();
        for (Appointment appointment : appointments) {
            appointmentCalendarDTOS.add(new AppointmentCalendarDTO(appointment));
        }
        return appointmentCalendarDTOS;
    }

    public String writeEmail(Appointment appointment, int recipientType){

        Set<Doctor> doctors = appointment.getDoctors();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dear ");
        if (recipientType == 1) {
            stringBuilder.append(appointment.getPatient().getFirstName());
            stringBuilder.append(" ");
            stringBuilder.append(appointment.getPatient().getFirstName());

        }
        if (recipientType == 2) {
            stringBuilder.append(appointment.getNurse().getFirstName());
            stringBuilder.append(" ");
            stringBuilder.append(appointment.getNurse().getFirstName());
        }
        if (recipientType == 3) {
            stringBuilder.append("doctor");
        }
        stringBuilder.append(", your operation has been scheduled for ");
        stringBuilder.append(appointment.getStartDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")));
        stringBuilder.append(" in ordination ");
        stringBuilder.append(appointment.getOrdination().getNumber());
        stringBuilder.append(", Clinic: ");
        stringBuilder.append(appointment.getOrdination().getClinic().getName());
        stringBuilder.append(", Address: ");
        stringBuilder.append(appointment.getOrdination().getClinic().getAddress());
        stringBuilder.append(". Doctors performing the operation are: ");
        for (Doctor d : doctors){
            stringBuilder.append(d.getFirstName());
            stringBuilder.append(" ");
            stringBuilder.append(d.getLastName());
            stringBuilder.append(", ");
        }
        String message = stringBuilder.toString();
        return message;
    }

    @Override
    public void sendOperationMail(Appointment appointment) {
        Nurse nurse = appointment.getNurse();
        Patient patient = appointment.getPatient();
        Set<Doctor> doctors = appointment.getDoctors();

        if (nurse == null || patient == null){
            return;
        }

        String subject = "Operation notice: Operation has been scheduled";
        String messagePatient = writeEmail(appointment, 1);
        String messageNurse = writeEmail(appointment, 2);
        String messageDoctor = writeEmail(appointment, 3);

        emailService.sendEmail(patient.getUsername(), subject, messagePatient);
        emailService.sendEmail(nurse.getUsername(), subject, messageNurse);
        for (Doctor d : doctors){
            emailService.sendEmail(d.getUsername(), subject, messageDoctor);
        }
    }

    @Override
    public List<AppointmentHistoryDTO> getHistoryApp(long id){

        Set<ExaminationReport> list = medicalRecordRepository.findByPatientId(id).getExaminationReports();
        List<AppointmentHistoryDTO> historyList= new ArrayList<>();

        for(ExaminationReport e : list){

             AppointmentHistoryDTO a = new AppointmentHistoryDTO(e.getAppointment().getStartDateTime().toString(), e.getAppointment().getEndDateTime().toString(),
                     e.getDoctor().getFirstName(),e.getDoctor().getLastName(), e.getAppointment().getPrice().getAppointmentEnum().toString(),e.getDoctor().getSpecialization().getName(),
                     e.getDiagnosis().getName(), e.getId(), e.getDoctor().getId(), e.getDoctor().getClinic().getId(), e.getDoctor().getClinic().getName()) ;


            historyList.add(a);
        }

            return historyList;
    }

    @Override
    public List<Ordination> getAvailableOrdinations(AvailableAppointmentDTO dto) {

        LocalDateTime start = dto.getStartDateTime();
        LocalDateTime end = dto.getEndDateTime();

        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.PREDEF_BOOKED);
        statuses.add(AppointmentStatus.APPROVED);

        ClinicAdmin ca = clinicAdminService.getLoggedInClinicAdmin();
        if (ca == null){
            return null;
        }

        Clinic c = ca.getClinic();
        if (c == null){
            return null;
        }

        AppointmentEnum appType;
        if (Integer.parseInt(dto.getAppType()) == 0){
            appType = AppointmentEnum.EXAMINATION;
        } else {
            appType = AppointmentEnum.OPERATION;
        }

        List<Ordination> ordinations = ordinationRepo.findByTypeAndClinicId(appType, c.getId());
        List<Ordination> availableOrdinations = new ArrayList<>();
        for (Ordination o:ordinations) {
            List<Appointment> ordinationAppointments = appointmentRepository.findByOrdinationIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndStatusIn(o.getId(),start,end,statuses);
            if (ordinationAppointments.size() == 0){
                availableOrdinations.add(o);
            }
        }

        return availableOrdinations;
    }

    @Override
    public Appointment createNew(NewAppointmentDTO dto) {

        ClinicAdmin ca = clinicAdminService.getLoggedInClinicAdmin();
        AppointmentRequest areq = appointmentRequestRepository.findById(Long.parseLong(dto.getAppReqId())).get();

        if (areq == null){
            return null;
        }

        if (ca == null){
            return null;
        }

        Clinic c = ca.getClinic();

        if (c == null){
            return null;
        }

        Appointment a = new Appointment();
        a.setStartDateTime(dto.getStartDateTime());
        a.setEndDateTime(dto.getEndDateTime());
        a.setOrdination(ordinationService.findById(Long.parseLong(dto.getOrdinationId())));
        a.setPatient(patientService.findById(Long.parseLong(dto.getPatientId())));
        a.getDoctors().add(doctorService.findById(Long.parseLong(dto.getDoctorId())));
        a.setNurse(nurseRepository.findOneById(7L)); //TODO prava sestra
        a.setDiscount(0);
        a.setPrice(appointmentPriceService.findById(Long.parseLong(dto.getPriceId())).get());
        a.setStatus(AppointmentStatus.APPROVED);
        a.setClinic(c);

        appointmentRepository.save(a);
        try {
            appointmentRequestRepository.deleteOneById(Long.parseLong(dto.getAppReqId()));
        } catch (Exception e){
            return a;
        }

        return a;
    }

    @Override
    public List<PredefinedAppointmentDTO> getFutureCancelAppointments(Long id){

         List<Appointment> list = appointmentRepository.findByPatientId(id);
         List<PredefinedAppointmentDTO> finalList = new ArrayList<>();
         for(Appointment a : list){
             if(a.getStatus().toString().equals("APPROVED") || a.getStatus().toString().equals("PREDEF_BOOKED")){
                 if(a.getStartDateTime().isAfter(LocalDateTime.now().plusHours(24))) {
                     PredefinedAppointmentDTO dto = new PredefinedAppointmentDTO(a, Long.parseLong("1"));
                     finalList.add(dto);
                 }
             }
        }
        return finalList;
    }

    @Override
    public List<PredefinedAppointmentDTO> getFutureFixAppointments(Long id){

        List<Appointment> list = appointmentRepository.findByPatientId(id);
        List<PredefinedAppointmentDTO> finalList = new ArrayList<>();
        for(Appointment a : list){
            if(a.getStatus().toString().equals("APPROVED") || a.getStatus().toString().equals("PREDEF_BOOKED")) {
                if (a.getStartDateTime().isAfter(LocalDateTime.now()) && a.getStartDateTime().isBefore(LocalDateTime.now().plusHours(24))) {
                    PredefinedAppointmentDTO dto = new PredefinedAppointmentDTO(a,  Long.parseLong("1") );
                    finalList.add(dto);
                }
            }
        }
        return finalList;
    }

    @Override
    public Appointment cancelAppointmentP(Long appointmentId) {
        Appointment appointment = getAppointment(appointmentId);
        if (appointment == null){
            return null;
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime canCancel = appointment.getStartDateTime().minusHours(24);
        if (now.isAfter(canCancel)){
            return null;
        }

        List<Doctor> appDoctors = new ArrayList<>();
        for(Doctor d: appointment.getDoctors()){
            appDoctors.add(d);
        }

        appointment.setStatus(AppointmentStatus.CANCELED);
        Nurse nurse = appointment.getNurse();
        Patient patient = appointment.getPatient();

        String subject = "Appointment notice: Your appointment has been canceled";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(patient.getFirstName());
        stringBuilder.append(" ");
        stringBuilder.append(patient.getLastName());
        stringBuilder.append("has canceled the appointment scheduled for ");
        stringBuilder.append(appointment.getStartDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")));

        String message = stringBuilder.toString();

        for (Doctor dr : appDoctors){
            emailService.sendEmail(dr.getUsername(), subject, message);
        }
        emailService.sendEmail(nurse.getUsername(), subject, message);

        return appointmentRepository.save(appointment);
    }


}
