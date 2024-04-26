package ru.grak.crm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grak.crm.dto.AbonentDto;
import ru.grak.crm.entity.Client;
import ru.grak.crm.service.ManagerService;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @PatchMapping("/changeTariff")
    public void changeTariff(String msisdn, String typeTariff) {
        managerService.changeTariff(msisdn, typeTariff);
    }

    @PostMapping("/create")
    public Client createAbonent(AbonentDto abonentDto) {
        return managerService.createAbonent(abonentDto);
    }

}
