package com.elecciones.senado.security.context.user.infrastructure.mappers;

import com.elecciones.senado.security.context.document_type.infrastructure.mappers.DocumentTypeMapper;
import com.elecciones.senado.security.context.user.application.dto.UserCreateDTO;
import com.elecciones.senado.security.context.user.domain.model.User;
import com.elecciones.senado.security.context.user.infrastructure.persistence.UserEntity;
import com.elecciones.senado.security.utils.mappers.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserCreateMapper implements Mapper<UserEntity, User, UserCreateDTO> {

    private final DocumentTypeMapper documentTypeMapper = new DocumentTypeMapper();

    @Override
    public User entityToModel(UserEntity entity) {
        return User.builder()
                .documentNumber(entity.getDocumentNumber())
                .documentType(documentTypeMapper.entityToModel(entity.getDocumentType()))
                .username(entity.getUsername())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .password(entity.getPassword())
                .build();
    }

    @Override
    public UserEntity modelToEntity(User model) {
        return UserEntity.builder()
                .documentNumber(model.getDocumentNumber())
                .documentType(documentTypeMapper.modelToEntity(model.getDocumentType()))
                .username(model.getUsername())
                .name(model.getName())
                .lastName(model.getLastName())
                .password(model.getPassword())
                .build();
    }

    @Override
    public UserCreateDTO modelToDto(User model) {
        return UserCreateDTO.builder()
                .documentNumber(model.getDocumentNumber())
                .documentType(documentTypeMapper.modelToDto(model.getDocumentType()))
                .username(model.getUsername())
                .name(model.getName())
                .lastName(model.getLastName())
                .password(model.getPassword())
                .build();
    }

    @Override
    public User dtoToModel(UserCreateDTO dto) {
        return User.builder()
                .documentNumber(dto.getDocumentNumber())
                .documentType(documentTypeMapper.dtoToModel(dto.getDocumentType()))
                .username(dto.getUsername())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
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
    public List<UserCreateDTO> modelsToDtos(List<User> models) {
        return models.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> dtosToModels(List<UserCreateDTO> dtos) {
        return dtos.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
