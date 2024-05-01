package com.elecciones.senado.security.context.document_type.application.usecase;

import com.elecciones.senado.security.context.document_type.domain.model.DocumentType;
import com.elecciones.senado.security.context.document_type.domain.port.DocumentTypeRepository;
import com.elecciones.senado.security.utils.constants.ErrorMessages;
import com.elecciones.senado.security.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllDocumentTypeUseCase {

    private final DocumentTypeRepository documentTypeRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public List<DocumentType> findAll() throws NoResultsException {
        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        if(documentTypes == null || documentTypes.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);
        return documentTypes;
    }

}
