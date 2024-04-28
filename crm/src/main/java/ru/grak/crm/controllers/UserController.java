package ru.grak.crm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grak.crm.dto.PaymentDto;
import ru.grak.crm.service.ClientService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final ClientService userService;

    @PatchMapping("/pay")
    public BigDecimal pay(PaymentDto payment) {
        return userService.pay(payment);
    }

}
