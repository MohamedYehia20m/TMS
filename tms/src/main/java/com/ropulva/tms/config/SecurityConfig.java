package com.ropulva.tms.config;

import com.ropulva.tms.service.CustomUserDetailsService;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers("/login").permitAll() // login
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()  // register

                        .requestMatchers("/api/users/me").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/users/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/tasks/**").hasRole("ADMIN")
                        .requestMatchers("/api/tasks/**").hasAnyRole("ADMIN","USER")

                        .requestMatchers(HttpMethod.GET, "/api/workspaces/**").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/api/workspaces/**").hasRole("ADMIN")


                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> basic.disable());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }


    @Bean
    public UserDetailsService userDetailsService(CustomUserDetailsService customUserDetailsService) {
        return customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}