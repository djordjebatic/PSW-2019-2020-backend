package com.example.pswbackend;

import com.example.pswbackend.controllers.*;
import com.example.pswbackend.repositories.AppointmentRepositoryTest;
import com.example.pswbackend.repositories.ClinicRepositoryTest;
import com.example.pswbackend.repositories.OrdinationRepositoryTest;
import com.example.pswbackend.selenium.SchedulePredefinedAppointment;
import com.example.pswbackend.selenium.ScheduleRegularAppointment;
import com.example.pswbackend.services.AppointmentServiceTest;
import com.example.pswbackend.services.DoctorServiceTest;
import com.example.pswbackend.services.OrdinationServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AppointmentControllerUnitTest.class,
        AppointmentRepositoryTest.class,
        ClinicRepositoryTest.class,
        OrdinationRepositoryTest.class,
        AppointmentServiceTest.class,
        OrdinationServiceTest.class,
        DoctorServiceTest.class,
        AppointmentControllerUnitTest.class,
        AppointmentControllerIntegrationTest.class,
        OrdinationControllerUnitTest.class,
        PredefinedAppointmentControllerUnitTest.class,
        //SchedulePredefinedAppointment.class,
        //ScheduleRegularAppointment.class,

})
public class ClinicCenterSuite {
}
