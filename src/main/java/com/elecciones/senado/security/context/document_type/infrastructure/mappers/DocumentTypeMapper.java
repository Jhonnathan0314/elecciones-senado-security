package com.elecciones.senado.security.context.document_type.infrastructure.mappers;

import com.elecciones.senado.security.context.document_type.application.dto.DocumentTypeDTO;
import com.elecciones.senado.security.context.document_type.domain.model.DocumentType;
import com.elecciones.senado.security.context.document_type.infrastructure.persistence.DocumentTypeEntity;
import com.elecciones.senado.security.utils.mappers.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentTypeMapper implements Mapper<DocumentTypeEntity, DocumentType, DocumentTypeDTO> {
    @Override
    public DocumentType entityToModel(DocumentTypeEntity entity) {
        return DocumentType.builder()
                .id(entity.getId())
                .prefix(entity.getPrefix())
                .name(entity.getName())
                .build();
    }

    @Override
    public DocumentTypeEntity modelToEntity(DocumentType model) {
        return DocumentTypeEntity.builder()
                .id(model.getId())
                .prefix(model.getPrefix())
                .name(model.getName())
                .build();
    }

    @Override
    public DocumentTypeDTO modelToDto(DocumentType model) {
        return DocumentTypeDTO.builder()
                .id(model.getId())
                .prefix(model.getPrefix())
                .name(model.getName())
                .build();
    }

    @Override
    public DocumentType dtoToModel(DocumentTypeDTO dto) {
        return DocumentType.builder()
                .id(dto.getId())
                .prefix(dto.getPrefix())
                .name(dto.getName())
                .build();
    }

    @Override
    public List<DocumentType> entitiesToModels(List<DocumentTypeEntity> entities) {
        return entities.stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentTypeEntity> modelsToEntities(List<DocumentType> models) {
        return models.stream()
                .map(this::modelToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentTypeDTO> modelsToDtos(List<DocumentType> models) {
        return models.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentType> dtosToModels(List<DocumentTypeDTO> dtos) {
        return dtos.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
