package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Long> {

    Drug findByName(String name);
    Drug findOneById(Long id);

}
