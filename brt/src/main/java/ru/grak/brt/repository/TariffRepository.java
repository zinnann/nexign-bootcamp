package ru.grak.brt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grak.brt.entity.Tariff;
import ru.grak.common.enums.TypeTariff;

public interface TariffRepository extends JpaRepository<Tariff, TypeTariff> {
}