package ru.grak.hrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grak.hrs.entity.CallCost;

@Repository
public interface CallCostRepository extends JpaRepository<CallCost, Long> {

    CallCost findByCallCostType(Long callCostType);
}