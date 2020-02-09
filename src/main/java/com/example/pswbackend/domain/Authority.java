package com.example.pswbackend.domain;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Authority implements GrantedAuthority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    @NonNull
    private String name;

    //@ManyToMany
    //private Set<Account> account;

    public Authority() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public Set<Account> getAccount() {
    //    return account;
    //}

    //public void setAccount(Set<Account> account) {
    //    this.account = account;
    //}

    @Override
    public String getAuthority() {
        return name;
    }
}