package com.ropulva.tms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class LoginController {
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(), // email as username
                        loginRequest.phone()  // phone as password
                )
        );


        // Set the authentication in the security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Verify the authorities
        System.out.println("Authorities: " + authentication.getAuthorities());

        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return ResponseEntity.status(200).body(new LoginResponse(authentication.getName(), roles));
    }

    public record LoginRequest(String email, String phone) {}

    public record LoginResponse(String username, String roles) {}
}