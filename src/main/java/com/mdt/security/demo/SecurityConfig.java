package com.mdt.security.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // setup basic authentication
        http.httpBasic(withDefaults())
                    .authorizeHttpRequests(auth -> {
                        try {
                            auth
                                    .requestMatchers("/api/user/create").hasAnyRole("USER")
                                    .requestMatchers("/index"
                                            ,"/favicon.ico"
                                            ,"/api/user/ping"
                                            ,"/say/helloNoAuth")
                                    .permitAll()
                                    .anyRequest()
                                    .authenticated();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
        // configure the csrf token to ignore the post request to create new users
        http.csrf(httpSecurityCsrfConfigurer ->
                httpSecurityCsrfConfigurer.ignoringRequestMatchers("/api/user/create"));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = CustomUserDetailsManager.instance.getInMemoryUserDetailsManager();
        UserDetails userDetails = User.withUsername("bsoni")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN", "USER")
                .build();
        inMemoryUserDetailsManager.createUser(userDetails);;
        return inMemoryUserDetailsManager;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
