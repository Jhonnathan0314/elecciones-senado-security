package com.elecciones.senado.security.context.user.application.dto;

import com.elecciones.senado.security.context.document_type.application.dto.DocumentTypeDTO;
import com.elecciones.senado.security.context.role.application.dto.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private Long documentNumber;
    private DocumentTypeDTO documentType;
    private String username;
    private String name;
    private String lastName;
    private String password;
    private RoleDTO role;

}
