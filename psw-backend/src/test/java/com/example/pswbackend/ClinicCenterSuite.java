package com.example.pswbackend;

import com.example.pswbackend.controllers.AppointmentControllerUnitTest;
import com.example.pswbackend.repositories.AppointmentRepositoryTest;
import com.example.pswbackend.repositories.ClinicRepositoryTest;
import com.example.pswbackend.repositories.OrdinationRepositoryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Djordje Batic
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AppointmentControllerUnitTest.class,
        AppointmentRepositoryTest.class,
        ClinicRepositoryTest.class,
        OrdinationRepositoryTest.class,
})
public class ClinicCenterSuite {
}
