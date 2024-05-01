package com.elecciones.senado.security.context.user.application.dto;

import com.elecciones.senado.security.context.document_type.application.dto.DocumentTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {

    private Long documentNumber;
    private DocumentTypeDTO documentType;
    private String username;
    private String name;
    private String lastName;
    private String password;

    @Override
    public String toString() {
        return "UserCreateDTO{" +
                "documentNumber=" + documentNumber +
                ", documentType=" + documentType.getPrefix() +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='******'" +
                '}';
    }
}
