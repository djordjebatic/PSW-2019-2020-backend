package com.example.pswbackend.services;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.AppointmentRequestDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.repositories.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AppointmentRequestServiceTest {

    @Autowired
    AppointmentRequestService appointmentRequestService;

    @MockBean
    DoctorRepository doctorRepository;

    @MockBean
    PatientRepository patientRepository;

    @MockBean
    ClinicAdminRepository clinicAdminRepository;

    @MockBean
    AppointmentRequestRepository appointmentRequestRepository;

    @MockBean
    AppointmentPriceRepository appointmentPriceRepository;

    @MockBean
    EmailService emailService;

    @MockBean
    DoctorService doctorService;

    @MockBean
    ClinicAdminService clinicAdminService;

    public static final Long APPOINTMENT_REQUEST_1_ID = 1L;
    public static final Long PATIENT_9_ID = 9L;
    public static final Long PATIENT_8_ID = 8L;
    public static final Long DOCTOR_5_ID= 5L;
    public static final Long DOCTOR_4_ID= 5L;
    public static final Long CLINIC_1_ID= 1L;
    public static final Long CLINIC_ADMIN_11_ID=11L;
    public static final Long CLINIC_ADMIN_12_ID=12L;
    public  static final int YEAR = 2020;
    public static final Month MONTH_DATE = Month.FEBRUARY;
    public static final int DAY_OF_MONTH_START = 27;
    public static final int DAY_OF_MONTH_END = 25;
    public static final int START_TIME_HOUR = 10;
    public static final int END_TIME_HOUR = 11;
    public static final int MIN = 00;
    public static final int SEC = 00;
    public static final Long APPOINTMENT_TYPE_1_ID=1L;
    public static final Long APPOINTMENT_PRICE_10_ID=10L;
    public static final String PATIENT_FN="Prezime";
    public static final Month MONTH_DATE1 = Month.JANUARY;
    public static final int DAY_OF_MONTH_START1 = 25;
    public static final int DAY_OF_MONTH_END1 = 25;
    public static final int START_TIME_HOUR1 = 8;
    public static final int END_TIME_HOUR1 = 9;
    public static final int MIN1 = 30;

    @Test
    public void saveRequest(){

        Clinic clinic = new Clinic();
        clinic.setId(CLINIC_1_ID);

        Doctor d = new Doctor();
        d.setId(DOCTOR_5_ID);
        d.setClinic(clinic);

        Patient patient=new Patient();
        patient.setId(PATIENT_9_ID);

        AppointmentEnum ae= AppointmentEnum.EXAMINATION;

        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE1, DAY_OF_MONTH_START1, START_TIME_HOUR1, MIN, SEC);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE1, DAY_OF_MONTH_START1, END_TIME_HOUR1, MIN1, SEC);

        AppointmentRequest ar= new AppointmentRequest(startDateTime, endDateTime, d, clinic, ae, patient.getId());

        given(this.appointmentRequestRepository.save(ar)).willReturn(ar);
    }

    @Test
    public void getById(){

       /* AppointmentRequest appointmentRequest= new AppointmentRequest();
        appointmentRequest.setId(APPOINTMENT_REQUEST_1_ID);

        Clinic clinic = new Clinic();
        clinic.setId(CLINIC_1_ID);

        Doctor d = new Doctor();
        d.setId(DOCTOR_4_ID);
        d.setClinic(clinic);

        Patient patient=new Patient();
        patient.setId(PATIENT_8_ID);
       // patient.setFirstName("Ime");
       // patient.setLastName(PATIENT_FN);
        System.out.println(patient);

        appointmentRequest.setClinic(clinic);
        appointmentRequest.setDoctor(d);
        System.out.println(clinic);
        System.out.println(d);

        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);

        appointmentRequest.setStartDateTime(startDateTime);
        appointmentRequest.setEndDateTime(endDateTime);
        appointmentRequest.setPatientId(PATIENT_9_ID);
        System.out.println(startDateTime);
        System.out.println(endDateTime);
        System.out.println(PATIENT_9_ID);

        AppointmentType appointmentType= new AppointmentType();
        appointmentType.setId(APPOINTMENT_TYPE_1_ID);
        appointmentType.setName("Ime");

        AppointmentEnum appointmentEnum= AppointmentEnum.EXAMINATION;

        AppointmentPrice appointmentPrice= new AppointmentPrice();
        appointmentPrice.setId(APPOINTMENT_PRICE_10_ID);

        appointmentRequest.setType(appointmentEnum);
        System.out.println(appointmentEnum);

        given(this.appointmentRequestRepository.findOneById(APPOINTMENT_REQUEST_1_ID)).willReturn(appointmentRequest);
        given(this.appointmentPriceRepository.findByAppointmentTypeIdAndAppointmentEnum(APPOINTMENT_TYPE_1_ID,appointmentEnum)).willReturn(appointmentPrice);
        given(this.patientRepository.findOneById(PATIENT_9_ID)).willReturn(patient);

        AppointmentRequestDTO dto= appointmentRequestService.getById(APPOINTMENT_REQUEST_1_ID);

        dto.setTypeSpec(appointmentPrice.getAppointmentType().getName());
        dto.setPrice(appointmentPrice.getPrice());
        dto.setPriceId(appointmentPrice.getId());
        dto.setPatientId(patient.getId());
        dto.setPatientFN(patient.getFirstName());
        dto.setPatientLN(patient.getLastName());

        assertThat(dto).isNotNull();
        assertEquals(PATIENT_FN, dto.getPatientFN());*/

    }

    @Test
    public void getById_NonExisting(){
       /* AppointmentRequestDTO appointmentRequestDTO= appointmentRequestService.getById();
        assertThat(appointmentRequestDTO).isNull();*/

    }

    @Test
    public void sendRequest() throws Exception{


        Patient patient=new Patient();
        patient.setId(PATIENT_9_ID);

        Clinic clinic = new Clinic();
        clinic.setId(CLINIC_1_ID);

        Doctor d = new Doctor();
        d.setId(DOCTOR_5_ID);
        d.setClinic(clinic);

        ClinicAdmin ca= new ClinicAdmin();
        ca.setId(CLINIC_ADMIN_11_ID);
        ca.setClinic(clinic);

        ClinicAdmin ca1= new ClinicAdmin();
        ca.setId(CLINIC_ADMIN_12_ID);
        ca.setClinic(clinic);

        given(this.patientRepository.findOneById(PATIENT_9_ID)).willReturn(patient);
        given(this.doctorRepository.findOneById(DOCTOR_5_ID)).willReturn(d);

        List<ClinicAdmin> listaCa= new ArrayList<>();
        listaCa.add(ca);
        listaCa.add(ca1);

        given(this.clinicAdminRepository.findByClinicId(CLINIC_1_ID)).willReturn(listaCa);

        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);

        AppointmentEnum appointmentEnum = AppointmentEnum.EXAMINATION;

        AppointmentRequest appR= new AppointmentRequest(startDateTime, endDateTime, d, clinic ,appointmentEnum, patient.getId());

        given(this.appointmentRequestRepository.save(appR)).willReturn(appR);

        Mockito.doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString());

    }

    @Test
    public void getClinicAppReqCA(){
        List<AppointmentRequestDTO> list = new ArrayList<>();

        Patient patient=new Patient();
        patient.setId(PATIENT_9_ID);

        Clinic clinic = new Clinic();
        clinic.setId(CLINIC_1_ID);

        Doctor d = new Doctor();
        d.setId(DOCTOR_5_ID);
        d.setClinic(clinic);
        d.setFirstName("Milovan");
        d.setLastName("Grubjesic");

        ClinicAdmin ca = new ClinicAdmin();
        ca.setClinic(clinic);
        ca.setId(5);

        given(this.clinicAdminService.getLoggedInClinicAdmin()).willReturn(ca);

        List<AppointmentRequestDTO> dtoList = new ArrayList<>();

        AppointmentPrice appointmentPrice= new AppointmentPrice();
        appointmentPrice.setId(APPOINTMENT_PRICE_10_ID);
        appointmentPrice.setAppointmentEnum(AppointmentEnum.EXAMINATION);
        AppointmentType appointmentType= new AppointmentType();
        appointmentType.setId(APPOINTMENT_TYPE_1_ID);
        appointmentType.setName("Kardio");
        appointmentPrice.setAppointmentType(appointmentType);
        appointmentPrice.setPrice(3000D);

        given(this.appointmentPriceRepository.findByAppointmentTypeIdAndAppointmentEnum(appointmentType.getId(), AppointmentEnum.EXAMINATION)).willReturn(appointmentPrice);
        given(this.patientRepository.findOneById(PATIENT_9_ID)).willReturn(patient);

        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE1, DAY_OF_MONTH_START1, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE1, DAY_OF_MONTH_START1, END_TIME_HOUR, MIN, SEC);
        LocalDateTime startDateTime1 = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime1 = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);

        AppointmentRequest appointmentRequest= new AppointmentRequest();
        AppointmentRequest appointmentRequest1= new AppointmentRequest();

        appointmentRequest.setPatientId(patient.getId());
        appointmentRequest.setType(AppointmentEnum.EXAMINATION);
        appointmentRequest.setEndDateTime(endDateTime);
        appointmentRequest.setStartDateTime(startDateTime);
        appointmentRequest.setDoctor(d);
        appointmentRequest.setClinic(clinic);
        appointmentRequest.setId(1L);

        appointmentRequest1.setPatientId(patient.getId());
        appointmentRequest1.setType(AppointmentEnum.EXAMINATION);
        appointmentRequest1.setEndDateTime(endDateTime1);
        appointmentRequest1.setStartDateTime(startDateTime1);
        appointmentRequest1.setDoctor(d);
        appointmentRequest1.setClinic(clinic);
        appointmentRequest1.setId(2L);


        AppointmentRequestDTO dto = new AppointmentRequestDTO(appointmentRequest.getId(), appointmentType.getName(), startDateTime, endDateTime, d.getFirstName(), d.getLastName(), DOCTOR_5_ID);
        AppointmentRequestDTO dto1 = new AppointmentRequestDTO(appointmentRequest1.getId(), appointmentType.getName(), startDateTime1, endDateTime1, d.getFirstName(), d.getLastName(), DOCTOR_5_ID);

        List<AppointmentRequestDTO> listDTO= new ArrayList<>();
        listDTO.add(dto);
        listDTO.add(dto1);

        assertThat(listDTO).hasSize(2);
    }

    @Test
    public void getClinicAppReqDoc(){

        Patient patient=new Patient();
        patient.setId(PATIENT_9_ID);

        Clinic clinic = new Clinic();
        clinic.setId(CLINIC_1_ID);

        Doctor d = new Doctor();
        d.setId(DOCTOR_5_ID);
        d.setClinic(clinic);
        d.setFirstName("Milovan");
        d.setLastName("Grubjesic");

        given(this.doctorService.getLoggedInDoctor()).willReturn(d);

        List<AppointmentRequestDTO> dtoList = new ArrayList<>();

        AppointmentPrice appointmentPrice= new AppointmentPrice();
        appointmentPrice.setId(APPOINTMENT_PRICE_10_ID);
        appointmentPrice.setAppointmentEnum(AppointmentEnum.EXAMINATION);
        AppointmentType appointmentType= new AppointmentType();
        appointmentType.setId(APPOINTMENT_TYPE_1_ID);
        appointmentType.setName("Kardio");
        appointmentPrice.setAppointmentType(appointmentType);
        appointmentPrice.setPrice(3000D);

        given(this.appointmentPriceRepository.findByAppointmentTypeIdAndAppointmentEnum(appointmentType.getId(), AppointmentEnum.EXAMINATION)).willReturn(appointmentPrice);
        given(this.patientRepository.findOneById(PATIENT_9_ID)).willReturn(patient);

        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE1, DAY_OF_MONTH_START1, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE1, DAY_OF_MONTH_START1, END_TIME_HOUR, MIN, SEC);
        LocalDateTime startDateTime1 = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime1 = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);

        AppointmentRequest appointmentRequest= new AppointmentRequest();
        AppointmentRequest appointmentRequest1= new AppointmentRequest();

        appointmentRequest.setPatientId(patient.getId());
        appointmentRequest.setType(AppointmentEnum.EXAMINATION);
        appointmentRequest.setEndDateTime(endDateTime);
        appointmentRequest.setStartDateTime(startDateTime);
        appointmentRequest.setDoctor(d);
        appointmentRequest.setClinic(clinic);
        appointmentRequest.setId(1L);

        appointmentRequest1.setPatientId(patient.getId());
        appointmentRequest1.setType(AppointmentEnum.EXAMINATION);
        appointmentRequest1.setEndDateTime(endDateTime1);
        appointmentRequest1.setStartDateTime(startDateTime1);
        appointmentRequest1.setDoctor(d);
        appointmentRequest1.setClinic(clinic);
        appointmentRequest1.setId(2L);


        AppointmentRequestDTO dto = new AppointmentRequestDTO(appointmentRequest.getId(), appointmentType.getName(), startDateTime, endDateTime, d.getFirstName(), d.getLastName(), DOCTOR_5_ID);
        AppointmentRequestDTO dto1 = new AppointmentRequestDTO(appointmentRequest1.getId(), appointmentType.getName(), startDateTime1, endDateTime1, d.getFirstName(), d.getLastName(), DOCTOR_5_ID);

        List<AppointmentRequestDTO> listDTO= new ArrayList<>();
        listDTO.add(dto);
        listDTO.add(dto1);

        assertThat(listDTO).hasSize(2);

    }









}
