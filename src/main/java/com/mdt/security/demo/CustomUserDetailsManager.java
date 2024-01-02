package com.mdt.security.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.HashMap;
@Slf4j
public class CustomUserDetailsManager implements UserDetailsService, UserDetailsManager, UserDetailsPasswordService {

    private final HashMap<String, UserDetails> userMap = new HashMap<>();
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("in load by username");
        try {
            return userMap.values()
                    .stream()
                    .filter(userDetails -> userDetails.getUsername().equalsIgnoreCase(username))
                    .findFirst()
                    .get();
        } catch (UsernameNotFoundException unfe) {
            throw  new UsernameNotFoundException("Username "+ username+" not found", unfe);
        }
    }

    @Override
    public void createUser(UserDetails user) {
        String id = "USR_"+System.currentTimeMillis();
        userMap.put(id, user);
        log.info("Added user with id ->" + id);
        log.info(user.getUsername());
        user.getAuthorities()
                .stream()
                .findFirst()
                .ifPresent(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        log.info("map size " + this.userMap.size());
        this.userMap.put(user.getUsername(), user);
        log.info("map size after " + this.userMap.size());
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
