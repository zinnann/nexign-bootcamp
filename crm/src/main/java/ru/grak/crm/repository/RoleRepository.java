package ru.grak.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grak.crm.entity.Role;
import ru.grak.crm.entity.RoleEnum;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum name);
}