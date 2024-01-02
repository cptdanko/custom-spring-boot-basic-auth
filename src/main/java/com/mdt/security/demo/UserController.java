package com.mdt.security.demo;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Log
public class UserController {
    // @Autowired
    private CustomUserDetailsManager customUserDetailsManager = new CustomUserDetailsManager();
    @GetMapping(value = "/ping")
    private ResponseEntity<CustomUser> pingUser() {
        List<GrantedAuthority> ROLE_USER = Collections
                .unmodifiableList(AuthorityUtils.createAuthorityList("ROLE_USER"));
        CustomUser cUser = new CustomUser("bsoni", "1234", ROLE_USER);
        return new ResponseEntity<>(cUser, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        log.info(user.getUsername());
        log.info(user.getPassword());
        log.info("About to create user");
        customUserDetailsManager.createUser(user);
        log.info("Successfully created the user");
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
