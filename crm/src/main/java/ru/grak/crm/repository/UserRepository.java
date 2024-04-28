package ru.grak.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grak.crm.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String name);
}