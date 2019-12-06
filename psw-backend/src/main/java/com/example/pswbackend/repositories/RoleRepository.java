package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Authority, Long> {

    Authority findByName(String name);
}
