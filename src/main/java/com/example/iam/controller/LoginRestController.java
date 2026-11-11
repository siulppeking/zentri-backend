package com.example.iam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.exception.UsernameOrPasswordInvalidException;
import com.example.iam.dto.LoginRequest;
import com.example.iam.dto.LoginResponse;
import com.example.iam.provider.JwtProvider;

import javax.validation.Valid;

@RestController
public class LoginRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public LoginRestController(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            String token = jwtProvider.generateToken(authentication);
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException ex) {
            throw new UsernameOrPasswordInvalidException("Username or password is incorrect 2");
        }
    }
}