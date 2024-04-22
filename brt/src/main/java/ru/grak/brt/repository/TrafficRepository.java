package ru.grak.brt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grak.brt.entity.Traffic;

import java.util.List;

@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Long> {

    List<Traffic> findAllByMsisdn(String msisdn);
}