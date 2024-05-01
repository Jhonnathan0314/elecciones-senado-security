package com.elecciones.senado.security.context.user.infrastructure.mappers;

import com.elecciones.senado.security.context.document_type.infrastructure.mappers.DocumentTypeMapper;
import com.elecciones.senado.security.context.document_type.infrastructure.mappers.DocumentTypeResponseMapper;
import com.elecciones.senado.security.context.role.infrastructure.mappers.RoleResponseMapper;
import com.elecciones.senado.security.context.user.application.dto.UserResponseDTO;
import com.elecciones.senado.security.context.user.domain.model.User;
import com.elecciones.senado.security.context.user.infrastructure.persistence.UserEntity;
import com.elecciones.senado.security.utils.mappers.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponseMapper implements Mapper<UserEntity, User, UserResponseDTO> {

    private final RoleResponseMapper roleResponseMapper = new RoleResponseMapper();
    private final DocumentTypeResponseMapper documentTypeMapper = new DocumentTypeResponseMapper();

    @Override
    public User entityToModel(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .documentNumber(entity.getDocumentNumber())
                .documentType(documentTypeMapper.entityToModel(entity.getDocumentType()))
                .username(entity.getUsername())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .role(roleResponseMapper.entityToModel(entity.getRole()))
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
                .role(roleResponseMapper.modelToEntity(model.getRole()))
                .build();
    }

    @Override
    public UserResponseDTO modelToDto(User model) {
        return UserResponseDTO.builder()
                .id(model.getId())
                .documentNumber(model.getDocumentNumber())
                .documentType(documentTypeMapper.modelToDto(model.getDocumentType()))
                .username(model.getUsername())
                .name(model.getName())
                .lastName(model.getLastName())
                .role(roleResponseMapper.modelToDto(model.getRole()))
                .build();
    }

    @Override
    public User dtoToModel(UserResponseDTO dto) {
        return User.builder()
                .id(dto.getId())
                .documentNumber(dto.getDocumentNumber())
                .documentType(documentTypeMapper.dtoToModel(dto.getDocumentType()))
                .username(dto.getUsername())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .role(roleResponseMapper.dtoToModel(dto.getRole()))
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
    public List<UserResponseDTO> modelsToDtos(List<User> models) {
        return models.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> dtosToModels(List<UserResponseDTO> dtos) {
        return dtos.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
