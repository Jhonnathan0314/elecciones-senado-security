package com.elecciones.senado.security.context.document_type.application.usecase;

import com.elecciones.senado.security.context.document_type.domain.model.DocumentType;
import com.elecciones.senado.security.context.document_type.domain.port.DocumentTypeRepository;
import com.elecciones.senado.security.utils.constants.ErrorMessages;
import com.elecciones.senado.security.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindByPrefixDocumentTypeUseCase {

    private final DocumentTypeRepository documentTypeRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public DocumentType findById(String prefix) throws NoResultsException {
        Optional<DocumentType> optionalDocumentType = documentTypeRepository.findByPrefix(prefix);
        if(optionalDocumentType.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);
        return optionalDocumentType.get();
    }

}
