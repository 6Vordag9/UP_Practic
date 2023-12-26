package com.example.pr5_4.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    STUDENT, ADMIN, TEACHER, GLAVA;
    @Override
    public String getAuthority()
    {
        return name();
    }
}
