package com.mdt.security.demo;

import org.springframework.security.core.GrantedAuthority;

public class Roles implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return null;
    }
}
