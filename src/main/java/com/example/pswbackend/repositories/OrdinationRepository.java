package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Ordination;
import com.example.pswbackend.enums.AppointmentEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdinationRepository extends JpaRepository<Ordination, Long> {

    Ordination findOneById(Long id);
    List<Ordination> findByClinicId(Long clinicId);

    //@Query(value = "SELECT * from Ordination o WHERE o.type = ?1", nativeQuery = true)
    List<Ordination> findByTypeAndClinicId(AppointmentEnum type, Long clinicId);

    @Query(value="DELETE FROM ordination o WHERE o.id = :id", nativeQuery = true)
    Boolean deleteOneById(Long id);

}
