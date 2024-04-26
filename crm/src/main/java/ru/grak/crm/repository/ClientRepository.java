package ru.grak.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grak.crm.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    boolean existsByPhoneNumber(String phoneNumber);
}
