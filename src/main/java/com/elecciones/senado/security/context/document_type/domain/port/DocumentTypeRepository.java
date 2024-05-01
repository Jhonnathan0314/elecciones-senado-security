package com.elecciones.senado.security.context.document_type.domain.port;

import com.elecciones.senado.security.context.document_type.domain.model.DocumentType;

import java.util.List;
import java.util.Optional;

public interface DocumentTypeRepository {

    List<DocumentType> findAll();
    Optional<DocumentType> findById(Integer id);
    Optional<DocumentType> findByPrefix(String prefix);

}
