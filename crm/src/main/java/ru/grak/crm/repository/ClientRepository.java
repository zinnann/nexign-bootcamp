package ru.grak.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grak.crm.entity.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Client> findByPhoneNumber(String phoneNumber);
}
