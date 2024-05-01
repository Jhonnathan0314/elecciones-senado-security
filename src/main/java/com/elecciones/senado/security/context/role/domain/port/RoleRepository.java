package com.elecciones.senado.security.context.role.domain.port;

import com.elecciones.senado.security.context.role.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    List<Role> findAll();
    Optional<Role> findById(Long id);
    Optional<Role> findByName(String name);
    Role create(Role role);
    Role update(Role role);
    void deleteById(Long id);
}
