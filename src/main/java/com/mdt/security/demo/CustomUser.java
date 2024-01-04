package com.mdt.security.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
@Getter
@Setter

public class CustomUser extends User {

    private String id;
    public CustomUser() {
        super("", "", new ArrayList<>());

    }
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
