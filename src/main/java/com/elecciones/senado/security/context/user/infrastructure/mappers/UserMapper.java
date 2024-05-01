package com.elecciones.senado.security.context.user.infrastructure.mappers;

import com.elecciones.senado.security.context.document_type.infrastructure.mappers.DocumentTypeMapper;
import com.elecciones.senado.security.context.role.infrastructure.mappers.RoleMapper;
import com.elecciones.senado.security.context.user.application.dto.UserDTO;
import com.elecciones.senado.security.context.user.domain.model.User;
import com.elecciones.senado.security.context.user.infrastructure.persistence.UserEntity;
import com.elecciones.senado.security.utils.mappers.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper implements Mapper<UserEntity, User, UserDTO> {

    private final RoleMapper roleMapper = new RoleMapper();
    private final DocumentTypeMapper documentTypeMapper = new DocumentTypeMapper();

    @Override
    public User entityToModel(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .documentNumber(entity.getDocumentNumber())
                .documentType(documentTypeMapper.entityToModel(entity.getDocumentType()))
                .username(entity.getUsername())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .password(entity.getPassword())
                .creationDate(entity.getCreationDate())
                .updateDate(entity.getUpdateDate())
                .state(entity.getState())
                .role(roleMapper.entityToModel(entity.getRole()))
                .build();
    }

    @Override
    public UserEntity modelToEntity(User model) {
        return UserEntity.builder()
                .id(model.getId())
                .documentNumber(model.getDocumentNumber())
                .documentType(documentTypeMapper.modelToEntity(model.getDocumentType()))
                .username(model.getUsername())
                .name(model.getName())
                .lastName(model.getLastName())
                .password(model.getPassword())
                .creationDate(model.getCreationDate())
                .updateDate(model.getUpdateDate())
                .state(model.getState())
                .role(roleMapper.modelToEntity(model.getRole()))
                .build();
    }

    @Override
    public UserDTO modelToDto(User model) {
        return UserDTO.builder()
                .id(model.getId())
                .documentNumber(model.getDocumentNumber())
                .documentType(documentTypeMapper.modelToDto(model.getDocumentType()))
                .username(model.getUsername())
                .name(model.getName())
                .lastName(model.getLastName())
                .password(model.getPassword())
                .role(roleMapper.modelToDto(model.getRole()))
                .build();
    }

    @Override
    public User dtoToModel(UserDTO dto) {
        return User.builder()
                .id(dto.getId())
                .documentNumber(dto.getDocumentNumber())
                .documentType(documentTypeMapper.dtoToModel(dto.getDocumentType()))
                .username(dto.getUsername())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .role(roleMapper.dtoToModel(dto.getRole()))
                .build();
    }

    @Override
    public List<User> entitiesToModels(List<UserEntity> entities) {
        return entities.stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserEntity> modelsToEntities(List<User> models) {
        return models.stream()
                .map(this::modelToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> modelsToDtos(List<User> models) {
        return models.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> dtosToModels(List<UserDTO> dtos) {
        return dtos.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
