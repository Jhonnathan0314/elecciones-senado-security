package com.elecciones.senado.security.context.document_type.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DocumentTypeDTO {

    private Integer id;
    private String prefix;
    private String name;

}
