package ru.grak.cdr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grak.cdr.entity.Abonent;

@Repository
public interface AbonentRepository extends JpaRepository<Abonent, Long> {

    Abonent findByPhoneNumber(String phoneNumber);
}