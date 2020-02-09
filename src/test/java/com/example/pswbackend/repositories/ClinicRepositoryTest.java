package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Clinic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class ClinicRepositoryTest {

    public static final String CLINIC_NAME = "GlObAl CaRe SuRgErY";

    @Autowired
    private ClinicRepository clinicRepository;

    @Test
    public void findByNameIgnoreCase() {
        List<Clinic> clinics = clinicRepository.findByNameIgnoreCase(CLINIC_NAME);
        assertEquals(1, clinics.size());
    }

    @Test
    public void findAll() {
        List<Clinic> clinics = clinicRepository.findAll();
        assertEquals(5, clinics.size());
    }
}