package com.elecciones.senado.security.context.document_type.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentTypeJpaRepository extends JpaRepository<DocumentTypeEntity, Integer> {

    Optional<DocumentTypeEntity> findByPrefix(String prefix);

}
