package ru.grak.crm.controllers.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grak.crm.dto.RegisterRequest;
import ru.grak.crm.service.auth.RegistrationService;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping(value = "api/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegisterRequest registerRequest) throws RoleNotFoundException {
        registrationService.registration(registerRequest);
        return ResponseEntity.ok("Success registration");
    }
}
