package com.example.pswbackend.services;


import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.ClinicDTO;
import com.example.pswbackend.dto.FilterClinicsDTO;
import com.example.pswbackend.dto.ResultClinicDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.repositories.AppointmentPriceRepository;
import com.example.pswbackend.repositories.AppointmentTypeRepository;
import com.example.pswbackend.repositories.ClinicRepository;
import com.example.pswbackend.repositories.DoctorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ClinicServiceTest {

    @Autowired
    ClinicService clinicService;

    @MockBean
    ClinicRepository clinicRepository;

    @MockBean
    DoctorRepository doctorRepository;

    @MockBean
    AppointmentTypeRepository appointmentTypeRepository;

    @MockBean
    AppointmentPriceRepository appointmentPriceRepository;

    @MockBean
    AppointmentService appointmentService;

    public static final int YEAR = 2020;
    public static final Month MONTH_DATE = Month.FEBRUARY;
    public static final Month MONTH_DATE1 = Month.MARCH;
    public static final Month MONTH_DATE2 = Month.OCTOBER;
    public static final int DAY_OF_MONTH_START = 27;
    public static final int START_TIME_HOUR = 10;
    public static final int HOUR = 8;
    public static final int END_TIME_HOUR = 11;
    public static final int MIN = 00;
    public static final int SEC = 00;
    public static final Long DOCTOR_5_ID = 5L;
    public static final Long DOCTOR_6_ID = 6L;
    public static final Long DOCTOR_7_ID = 7L;
    public static final long ORDINATION_4_ID = 4L;
    public static final Long CLINIC_1_ID=1L;
    public static final Long CLINIC_2_ID=2L;
    public static final Long CLINIC_3_ID=3L;
    public static final Long APPOINTMENT_TYPE_5_ID=5L;

    @Test
    public void findById(){

    }
    @Test
    public void findClinicById(){

    }
    @Test
    public void findByClinicAdminId(){

    }
    @Test
    public void findByName(){

    }
    @Test
    public void findByNameIgnoreCase(){

    }
    @Test
    public void register(){

    }
    @Test
    public void findAll(){

    }

    @Test
    public void filterClinics(){

        Clinic clinic = new Clinic();
        clinic.setId(CLINIC_1_ID);
        Clinic clinic1 = new Clinic();
        clinic1.setId(CLINIC_2_ID);
        Clinic clinic2 = new Clinic();
        clinic2.setId(CLINIC_3_ID);

        List<Clinic> clinicList= new ArrayList<>();
        clinicList.add(clinic);
        clinicList.add(clinic1);
        clinicList.add(clinic2);


        Doctor d = new Doctor();
        d.setId(DOCTOR_5_ID);
        d.setClinic(clinic);
        AppointmentType appointmentType = new AppointmentType();
        appointmentType.setId(APPOINTMENT_TYPE_5_ID);
        appointmentType.setName("Otorinolaringologija");
        appointmentType.setClinic(clinic);
        d.setSpecialization(appointmentType);
        LocalTime localTime= LocalTime.of(HOUR,MIN,SEC);
        d.setWorkTimeStart(localTime);
        Doctor d1 = new Doctor();
        d1.setId(DOCTOR_6_ID);
        d1.setClinic(clinic1);
        AppointmentType appointmentType1 = new AppointmentType();
        appointmentType1.setId(APPOINTMENT_TYPE_5_ID);
        appointmentType1.setName("Kardiologija");
        appointmentType1.setClinic(clinic);
        d.setSpecialization(appointmentType);
        LocalTime localTime1= LocalTime.of(HOUR,MIN);
        d.setWorkTimeStart(localTime);

        List<AppointmentType> appointmentTypes= new ArrayList<>();
        appointmentTypes.add(appointmentType);
        appointmentTypes.add(appointmentType1);
        clinic.setAppointmentTypes(appointmentTypes);

        List<Doctor> doctors= new ArrayList<>();
        doctors.add(d);
        doctors.add(d1);

        FilterClinicsDTO filterClinicsDTO=new FilterClinicsDTO();
        filterClinicsDTO.setType("Otorinolaringologija");
        LocalDate date = LocalDate.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START);
        filterClinicsDTO.setDate(date);

        LocalDateTime localDateTime= LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, HOUR, MIN, SEC);

        AppointmentPrice appointmentPrice = new AppointmentPrice();
        appointmentPrice.setId(1L);
        appointmentPrice.setAppointmentEnum(AppointmentEnum.EXAMINATION);
        appointmentPrice.setAppointmentType(appointmentType);

        List<Appointment> appointmentList= new ArrayList<>();

        given(this.clinicRepository.findAll()).willReturn(clinicList);
        given(this.doctorRepository.findByClinicId(CLINIC_1_ID)).willReturn(doctors);
        given(this.clinicRepository.findOneById(CLINIC_1_ID)).willReturn(clinic);
        given(this.appointmentTypeRepository.findByNameAndClinicId("Otorinolaringologija",CLINIC_1_ID)).willReturn(appointmentType);
        given(this.appointmentPriceRepository.findByAppointmentTypeIdAndAppointmentEnum(APPOINTMENT_TYPE_5_ID, AppointmentEnum.EXAMINATION)).willReturn(appointmentPrice);
        given(this. appointmentService.getDoctorAppointmentsDuringTheDay(DOCTOR_5_ID, localDateTime)).willReturn(appointmentList);
        given(this.clinicRepository.findOneById(CLINIC_1_ID)).willReturn(clinic);

        List<ResultClinicDTO> resultList= new ArrayList<>();
        resultList=clinicService.filterClinics(filterClinicsDTO);

        assertThat(resultList).isNotNull();
        assertThat(resultList).hasSize(1);


    }
    @Test
    public void updateClinic(){

    }

}
