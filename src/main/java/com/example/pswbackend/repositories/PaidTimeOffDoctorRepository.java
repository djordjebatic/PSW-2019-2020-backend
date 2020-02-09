package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.PaidTimeOffDoctor;
import com.example.pswbackend.enums.PaidTimeOffStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaidTimeOffDoctorRepository extends JpaRepository<PaidTimeOffDoctor, Long> {

    List<PaidTimeOffDoctor> findByPaidTimeOffStatus(PaidTimeOffStatus status);

}
