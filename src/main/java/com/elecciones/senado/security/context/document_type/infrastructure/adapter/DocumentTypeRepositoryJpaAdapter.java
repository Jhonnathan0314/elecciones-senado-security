package com.elecciones.senado.security.context.document_type.infrastructure.adapter;

import com.elecciones.senado.security.context.document_type.domain.model.DocumentType;
import com.elecciones.senado.security.context.document_type.domain.port.DocumentTypeRepository;
import com.elecciones.senado.security.context.document_type.infrastructure.mappers.DocumentTypeMapper;
import com.elecciones.senado.security.context.document_type.infrastructure.persistence.DocumentTypeEntity;
import com.elecciones.senado.security.context.document_type.infrastructure.persistence.DocumentTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DocumentTypeRepositoryJpaAdapter implements DocumentTypeRepository {

    private final DocumentTypeJpaRepository documentTypeJpaRepository;
    private final DocumentTypeMapper mapper = new DocumentTypeMapper();

    @Override
    public List<DocumentType> findAll() {
        List<DocumentTypeEntity> documentTypeEntities = documentTypeJpaRepository.findAll();
        return mapper.entitiesToModels(documentTypeEntities);
    }

    @Override
    public Optional<DocumentType> findById(Integer id) {
        Optional<DocumentTypeEntity> optDocumentTypeEntity = documentTypeJpaRepository.findById(id);
        return optDocumentTypeEntity.map(mapper::entityToModel);
    }

    @Override
    public Optional<DocumentType> findByPrefix(String prefix) {
        Optional<DocumentTypeEntity> optDocumentTypeEntity = documentTypeJpaRepository.findByPrefix(prefix);
        return optDocumentTypeEntity.map(mapper::entityToModel);
    }
}
