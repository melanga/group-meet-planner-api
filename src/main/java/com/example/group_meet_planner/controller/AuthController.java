package com.example.group_meet_planner.controller;

import com.example.group_meet_planner.entity.AppUser;
import com.example.group_meet_planner.model.LoginRequest;
import com.example.group_meet_planner.service.AuthService;
import com.example.group_meet_planner.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final TokenService tokenService;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, AuthService authService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(AppUser appUser) {
        return authService.register(appUser);
    }

    @PostMapping("/token")
    public String token(@RequestBody LoginRequest userLogin) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password()));
        return tokenService.generateToken(authentication);
    }
}
