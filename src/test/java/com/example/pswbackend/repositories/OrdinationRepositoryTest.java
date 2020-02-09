package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Ordination;
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
public class OrdinationRepositoryTest {

    public static final Long CLINIC_2_ID = 2L;
    public static final int NUMBER_OF_ORD_IN_CLINIC_2 = 4;

    @Autowired
    private OrdinationRepository ordinationRepository;

    @Test
    public void findByClinicId() {
        List<Ordination> ordinationList = ordinationRepository.findByClinicId(CLINIC_2_ID);
        assertEquals(5, ordinationList.size());

        for (Ordination ordination : ordinationList){
            assertEquals(CLINIC_2_ID, ordination.getClinic().getId());
        }
    }
}