package com.elecciones.senado.security.context.document_type.presentation.controller;

import com.elecciones.senado.security.context.document_type.application.dto.DocumentTypeResponseDTO;
import com.elecciones.senado.security.context.document_type.application.usecase.FindAllDocumentTypeUseCase;
import com.elecciones.senado.security.context.document_type.application.usecase.FindByIdDocumentTypeUseCase;
import com.elecciones.senado.security.context.document_type.domain.model.DocumentType;
import com.elecciones.senado.security.context.document_type.infrastructure.mappers.DocumentTypeResponseMapper;
import com.elecciones.senado.security.utils.exceptions.*;
import com.elecciones.senado.security.utils.http.HttpUtils;
import com.elecciones.senado.security.utils.messages.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/security/doc-type")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DocumentTypeController {

    private final FindAllDocumentTypeUseCase findAllDocumentTypeUseCase;
    private final FindByIdDocumentTypeUseCase findByIdDocumentTypeUseCase;

    private final DocumentTypeResponseMapper documentTypeResponseMapper = new DocumentTypeResponseMapper();
    private final HttpUtils httpUtils = new HttpUtils();

    @GetMapping
    public ResponseEntity<ApiResponse<List<DocumentTypeResponseDTO>>> findAll() {
        ApiResponse<List<DocumentTypeResponseDTO>> response = new ApiResponse<>();
        try {
            List<DocumentTypeResponseDTO> documentTypes = documentTypeResponseMapper.modelsToDtos(findAllDocumentTypeUseCase.findAll());
            response.setData(documentTypes);
            return ResponseEntity.ok(response);
        } catch (NoResultsException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DocumentTypeResponseDTO>> findById(@PathVariable Integer id) {
        ApiResponse<DocumentTypeResponseDTO> response = new ApiResponse<>();
        try {
            DocumentType documentType = findByIdDocumentTypeUseCase.findById(id);
            response.setData(documentTypeResponseMapper.modelToDto(documentType));
            return ResponseEntity.ok(response);
        } catch (NoResultsException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }
    
}
