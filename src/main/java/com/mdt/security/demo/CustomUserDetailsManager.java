package com.mdt.security.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.HashMap;
import java.util.NoSuchElementException;

@Slf4j
public class CustomUserDetailsManager implements UserDetailsService, UserDetailsManager, UserDetailsPasswordService {

    private final HashMap<String, UserDetails> userMap = new HashMap<>();
    public static final CustomUserDetailsManager instance = new CustomUserDetailsManager();
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("in load by username");
        log.info("The size of the userMap "+ userMap.values().size());
        log.info(username);
        log.info("************************************");
        return userMap.values()
                .stream()
                .filter(userDetails -> userDetails.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void createUser(UserDetails user) {
        String id = "USR_"+System.currentTimeMillis();
        // log.info("About to print before");
        // userMap.values().forEach(userDetails -> log.info(userDetails.getUsername()));
        // log.info(user.getUsername());
        userMap.put(user.getUsername(), user);
        //log.info("map size after " + userMap.size());
        // userMap.values().forEach(userDetails -> log.info(userDetails.getUsername()));
    }

    @Override
    public void updateUser(UserDetails user) {
        UserDetails ud = loadUserByUsername(user.getUsername());
        log.info("Found the userDetails object for supplied username");
        log.info(ud.getUsername());
        log.info("**************************8");
    }

    @Override
    public void deleteUser(String username) {
        log.info("DELETE user called");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        log.info("In user exists method");
        log.info("Does this get called on login?");
        return (loadUserByUsername(username) != null);
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }
}
