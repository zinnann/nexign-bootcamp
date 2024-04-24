package ru.grak.hrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grak.common.enums.TypeTariff;
import ru.grak.hrs.entity.Tariff;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, TypeTariff> {

    Tariff findByTariff(TypeTariff tariff);
}