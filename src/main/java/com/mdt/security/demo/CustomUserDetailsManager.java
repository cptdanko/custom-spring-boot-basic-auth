package com.mdt.security.demo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Slf4j
@Data
public class CustomUserDetailsManager {
    /**
     * To be replaced with a full database implementation
     * until then, this class will use the InMemoryUserDetailsManager.
     * InMemoryUserDetailsManager is not exposed directly but via a
     * class instance following the Singleton pattern.
     */
    private InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

    public static final CustomUserDetailsManager instance = new CustomUserDetailsManager();

}
