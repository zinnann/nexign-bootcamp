package ru.grak.brt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grak.brt.entity.Tariff;
import ru.grak.common.enums.TypeTariff;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, TypeTariff> {
}