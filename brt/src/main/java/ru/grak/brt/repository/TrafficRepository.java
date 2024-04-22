package ru.grak.brt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grak.brt.entity.Traffic;

import java.util.List;

public interface TrafficRepository extends JpaRepository<Traffic, Long> {

    List<Traffic> findAllByMsisdn(String msisdn);
}