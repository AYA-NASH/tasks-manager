package com.ropulva.taskmanager.googleCalender;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .anyRequest().permitAll() // Permit all requests
                )
                .csrf(csrf ->
                        csrf.ignoringRequestMatchers("/api/tasks/**") // Disable CSRF protection for URLs starting with /api/tasks
                );


        return http.build();
    }
}