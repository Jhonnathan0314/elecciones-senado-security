package com.elecciones.senado.security.context.user.application.dto;

import com.elecciones.senado.security.context.document_type.application.dto.DocumentTypeResponseDTO;
import com.elecciones.senado.security.context.role.application.dto.RoleResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private Long documentNumber;
    private DocumentTypeResponseDTO documentType;
    private String username;
    private String name;
    private String lastName;
    private RoleResponseDTO role;

}
