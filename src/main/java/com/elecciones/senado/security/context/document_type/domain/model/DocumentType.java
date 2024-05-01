package com.elecciones.senado.security.context.document_type.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentType {

    private Integer id;
    private String prefix;
    private String name;

    public boolean isValid(DocumentType documentType) {
        if(documentType.getPrefix() == null) return false;
        return !documentType.getPrefix().isEmpty();
    }

    @Override
    public String toString() {
        return "DocumentType{" +
                "id=" + id +
                ", prefix='" + prefix + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
