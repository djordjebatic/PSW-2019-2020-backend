package com.example.pswbackend.domain;

import com.example.pswbackend.enums.RoleEnum;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    @NonNull
    private RoleEnum name;

    @ManyToMany
    private Set<Account> account;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name.name();
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }

    public Set<Account> getAccount() {
        return account;
    }

    public void setAccount(Set<Account> account) {
        this.account = account;
    }

    @Override
    public String getAuthority() {
        return name.name();
    }
}