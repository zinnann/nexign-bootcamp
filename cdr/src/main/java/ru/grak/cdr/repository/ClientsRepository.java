package ru.grak.cdr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grak.cdr.entity.Clients;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long> {
}