package com.example.pswbackend.services;

import com.example.pswbackend.domain.Ordination;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface OrdinationService {

    Ordination findById(long id);

    List<Ordination> findAll();

}
