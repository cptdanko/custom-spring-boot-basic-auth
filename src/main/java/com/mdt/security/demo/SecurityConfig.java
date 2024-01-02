package com.mdt.security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(withDefaults())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/user/create").hasAnyRole("USER")
                            .requestMatchers("/index"
                                    , "/login"
                                    , "/logout"
                                    ,"/favicon.ico"
                                    ,"/api/user/ping")
                            .permitAll()

                            .anyRequest()
                            .authenticated());
        // http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        CustomUserDetailsManager inMemoryUserDetailsManager = new CustomUserDetailsManager();
        UserDetails userDetails = User.withUsername("bsoni")
                .password(passwordEncoder().encode("bs1234"))
                // .authorities("read")
                .roles("ADMINISTRATOR", "USER")
                .build();
        inMemoryUserDetailsManager.createUser(userDetails);;
        return inMemoryUserDetailsManager;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
