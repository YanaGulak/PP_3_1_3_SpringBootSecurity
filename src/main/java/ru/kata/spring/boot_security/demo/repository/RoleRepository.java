package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Repository
public interface RoleRepository {
    List<Role> findAll();

    Role findRoleById(Long id);

    Role findRoleByRoleName(String role);
}
