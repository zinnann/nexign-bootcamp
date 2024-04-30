package ru.grak.crm.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.grak.crm.dto.AbonentDto;
import ru.grak.crm.entity.Client;
import ru.grak.crm.service.ManagerService;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
@Tag(name = "Manager Controller", description = "APIs for managing subscribers")
public class ManagerController {

    private final ManagerService managerService;

    @PatchMapping("/change-tariff")
    public void changeTariff(@RequestBody String msisdn, @RequestBody String typeTariff) {
        managerService.changeTariff(msisdn, typeTariff);
    }

    @PostMapping("/create")
    public Client createAbonent(@RequestBody AbonentDto abonentDto) {
        return managerService.createAbonent(abonentDto);
    }

}
