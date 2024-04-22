package ru.grak.brt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grak.brt.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    Client findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);
}
