package com.schedule.auth.controller;

import com.schedule.auth.dto.*;
import com.schedule.auth.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterAuthResponse> register(@Valid @RequestBody RegisterAuthRequest request) {
        SessionAuth sessionAuth = authService.register(request);

        RegisterAuthResponse response = new RegisterAuthResponse(sessionAuth.getId(), sessionAuth.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginAuthResponse> login(@Valid @RequestBody LoginAuthRequest request, HttpSession session) {
        SessionAuth sessionAuth = authService.login(request);
        session.setAttribute("loginAuth", sessionAuth);

        LoginAuthResponse response = new LoginAuthResponse(
                sessionAuth.getId(),
                sessionAuth.getEmail()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@SessionAttribute(name = "loginAuth", required = false) SessionAuth sessionAuth, HttpSession session) {
        if (sessionAuth == null) {
            return ResponseEntity.badRequest().build();
        }

        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
