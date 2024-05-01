package com.elecciones.senado.security.context.role.infrastructure.mappers;

import com.elecciones.senado.security.context.role.application.dto.RoleUpdateDTO;
import com.elecciones.senado.security.context.role.domain.model.Role;
import com.elecciones.senado.security.context.role.infrastructure.persistence.RoleEntity;
import com.elecciones.senado.security.utils.mappers.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class RoleUpdateMapper implements Mapper<RoleEntity, Role, RoleUpdateDTO> {

    @Override
    public Role entityToModel(RoleEntity entity) {
        return Role.builder()
                .id(entity.getId())
                .name(entity.getName())
                .state(entity.getState())
                .build();
    }

    @Override
    public RoleEntity modelToEntity(Role model) {
        return RoleEntity.builder()
                .id(model.getId())
                .name(model.getName())
                .state(model.getState())
                .build();
    }

    @Override
    public RoleUpdateDTO modelToDto(Role model) {
        return RoleUpdateDTO.builder()
                .id(model.getId())
                .name(model.getName())
                .state(model.getState())
                .build();
    }

    @Override
    public Role dtoToModel(RoleUpdateDTO dto) {
        return Role.builder()
                .id(dto.getId())
                .name(dto.getName())
                .state(dto.getState())
                .build();
    }

    @Override
    public List<Role> entitiesToModels(List<RoleEntity> entities) {
        return entities.stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleEntity> modelsToEntities(List<Role> models) {
        return models.stream()
                .map(this::modelToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleUpdateDTO> modelsToDtos(List<Role> models) {
        return models.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Role> dtosToModels(List<RoleUpdateDTO> dtos) {
        return dtos.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
