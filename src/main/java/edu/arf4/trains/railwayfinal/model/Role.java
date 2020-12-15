package edu.arf4.trains.railwayfinal.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.CollectionTable;
import javax.persistence.Embeddable;
import java.util.Collection;
import java.util.Collections;


public enum Role {

    ADMIN, USER;


    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(name()));
    }


}

