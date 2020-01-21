package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface DrugRepository extends JpaRepository<Drug, Long> {

    Drug findByName(String name);

    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    //@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    Drug findOneById(Long id);
    void deleteOneById(Long id);
}
