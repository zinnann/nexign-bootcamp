package ru.grak.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grak.common.enums.TypeTariff;
import ru.grak.crm.entity.Tariff;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, TypeTariff> {

    boolean existsByTariff(TypeTariff tariff);
}