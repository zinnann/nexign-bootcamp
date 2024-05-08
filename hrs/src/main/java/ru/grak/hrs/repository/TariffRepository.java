package ru.grak.hrs.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grak.common.enums.TypeTariff;
import ru.grak.hrs.entity.Tariff;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, TypeTariff> {

    @Cacheable("tariff")
    Tariff findByTariff(TypeTariff tariff);
}