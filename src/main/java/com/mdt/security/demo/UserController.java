package com.mdt.security.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @GetMapping(value = "/ping")
    private ResponseEntity<CustomUser> pingUser() {
        List<GrantedAuthority> ROLE_USER = Collections
                .unmodifiableList(AuthorityUtils.createAuthorityList("ROLE_USER"));
        CustomUser cUser = new CustomUser("bsoni", "1234", ROLE_USER);
        return new ResponseEntity<>(cUser, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody CreateUserRequest createUserRequest) {
        String ROLE_PREFIX = "ROLE_";
        log.info(createUserRequest.toString());
        log.info("About to create user");
        if(ObjectUtils.isEmpty(createUserRequest)) {
            return new ResponseEntity<>("Please supply create user body", HttpStatus.BAD_REQUEST);
        }
        List<GrantedAuthority> auths = new ArrayList<>();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        for(String role: createUserRequest.getRoles()) {
            auths.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.toUpperCase()));
        }

        CustomUser cUser = new CustomUser(createUserRequest.getUsername(), bCryptPasswordEncoder.encode(createUserRequest.getPassword()), auths);
        log.info(cUser.toString());
        InMemoryUserDetailsManager inMemoryUserDetailsManager = CustomUserDetailsManager.instance.getInMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(cUser);
        log.info("Successfully created the user");
        return new ResponseEntity<>(cUser, HttpStatus.CREATED);
    }
    @GetMapping("/test2")
    public ResponseEntity<String> test2() {
        return new ResponseEntity<>("Doing another test", HttpStatus.OK);
    }
}
