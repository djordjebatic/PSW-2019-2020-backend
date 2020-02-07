package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.PaidTimeOffDoctor;
import com.example.pswbackend.enums.PaidTimeOffStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class PaidTimeOffDoctorRepositoryTest {

    @Autowired
    PaidTimeOffDoctorRepository paidTimeOffDoctorRepository;

    @Test
    public void findByPaidTimeOffStatus_whenThereAreInstancesWithThatStatus_returnsListOfPaidTimeOffDoctorRepository() {

        PaidTimeOffStatus status = PaidTimeOffStatus.REQUESTED;
        List<PaidTimeOffDoctor> list = paidTimeOffDoctorRepository.findByPaidTimeOffStatus(status);

        assertEquals(2,list.size());
    }

    @Test
    public void findByPaidTimeOffStatus_whenThereAreNoInstancesWithThatStatus_returnsEmptyList() {

        PaidTimeOffStatus status = PaidTimeOffStatus.DENIED;
        List<PaidTimeOffDoctor> list = paidTimeOffDoctorRepository.findByPaidTimeOffStatus(status);

        assertEquals(0,list.size());
    }
}
