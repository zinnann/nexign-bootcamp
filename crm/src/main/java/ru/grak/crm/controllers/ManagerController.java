package ru.grak.crm.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.grak.crm.dto.AbonentDto;
import ru.grak.crm.dto.ChangeTariffDto;
import ru.grak.crm.entity.Client;
import ru.grak.crm.service.ManagerService;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
@Tag(name = "Manager Controller", description = "APIs for managing subscribers")
public class ManagerController {

    private final ManagerService managerService;

    @PatchMapping("/change-tariff")
    public Client changeTariff(@RequestBody ChangeTariffDto changeTariffDto) {
        return managerService.changeTariff(changeTariffDto);
    }

    @PostMapping("/save")
    public Client createAbonent(@RequestBody AbonentDto abonentDto) {
        return managerService.createAbonent(abonentDto);
    }

}
