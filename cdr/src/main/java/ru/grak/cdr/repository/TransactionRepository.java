package ru.grak.cdr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grak.cdr.entity.CallDataRecord;

@Repository
public interface TransactionRepository extends JpaRepository<CallDataRecord,Long> {
}
