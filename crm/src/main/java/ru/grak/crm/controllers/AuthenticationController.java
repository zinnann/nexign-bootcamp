package ru.grak.crm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grak.crm.dto.auth.JwtResponse;
import ru.grak.crm.dto.auth.LoginRequest;
import ru.grak.crm.dto.auth.RegisterRequest;
import ru.grak.crm.service.auth.AuthenticationService;
import ru.grak.crm.service.auth.RegistrationService;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping(value = "api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegisterRequest registerRequest) throws RoleNotFoundException {
        registrationService.registration(registerRequest);
        return ResponseEntity.ok("Success registration");
    }

}
