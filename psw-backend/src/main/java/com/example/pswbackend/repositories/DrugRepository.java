package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugRepository extends JpaRepository<Drug, Long> {

    Drug findByName(String name);

    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    //@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    Drug findOneById(Long id);
    void deleteOneById(Long id);
    List<Drug> findAllByOrderByIdAsc();

}
