package com.elecciones.senado.security.context.role.infrastructure.adapter;

import com.elecciones.senado.security.context.role.domain.model.Role;
import com.elecciones.senado.security.context.role.domain.port.RoleRepository;
import com.elecciones.senado.security.context.role.infrastructure.mappers.RoleMapper;
import com.elecciones.senado.security.context.role.infrastructure.persistence.RoleEntity;
import com.elecciones.senado.security.context.role.infrastructure.persistence.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryJpaAdapter implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;
    private final RoleMapper mapper = new RoleMapper();

    @Override
    public List<Role> findAll() {
        List<RoleEntity> roleEntities = roleJpaRepository.findAll();
        return mapper.entitiesToModels(roleEntities);
    }

    @Override
    public Optional<Role> findById(Long id) {
        Optional<RoleEntity> optRoleEntity = roleJpaRepository.findById(id);
        return optRoleEntity.map(mapper::entityToModel);
    }

    @Override
    public Optional<Role> findByName(String name) {
        Optional<RoleEntity> optRoleEntity = roleJpaRepository.findByName(name);
        return optRoleEntity.map(mapper::entityToModel);
    }

    @Override
    public Role create(Role role) {
        RoleEntity roleEntity = roleJpaRepository.save(mapper.modelToEntity(role));
        return mapper.entityToModel(roleEntity);
    }

    @Override
    public Role update(Role role) {
        RoleEntity roleEntity = roleJpaRepository.save(mapper.modelToEntity(role));
        return mapper.entityToModel(roleEntity);
    }

    @Override
    public void deleteById(Long id) {
        roleJpaRepository.deleteById(id);
    }

}
