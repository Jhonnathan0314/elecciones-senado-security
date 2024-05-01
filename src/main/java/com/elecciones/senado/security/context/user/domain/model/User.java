package com.elecciones.senado.security.context.user.domain.model;

import com.elecciones.senado.security.context.document_type.domain.model.DocumentType;
import com.elecciones.senado.security.context.role.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    private Long id;
    private Long documentNumber;
    private DocumentType documentType;
    private String username;
    private String name;
    private String lastName;
    private String password;
    private Timestamp creationDate;
    private Timestamp updateDate;
    private String state;
    private Role role;

    public boolean isValid(User user) {
        if(user.getDocumentType() == null ||
                user.getDocumentNumber() == null ||
                user.getUsername() == null ||
                user.getName() == null ||
                user.getLastName() == null) return false;

        if (user.getDocumentNumber() <= 0) return false;
        if(!user.getDocumentType().isValid(user.getDocumentType())) return false;

        return !user.getUsername().isEmpty() &&
                !user.getName().isEmpty() &&
                !user.getLastName().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(
                getId(), user.getId()) &&
                Objects.equals(getDocumentNumber(), user.getDocumentNumber()) &&
                Objects.equals(getDocumentType().getPrefix(), user.getDocumentType().getPrefix()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getLastName(), user.getLastName());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", documentNumber=" + documentNumber +
                ", documentType=" + documentType.getPrefix() +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", state='" + state + '\'' +
                ", role=" + role.getName() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDocumentNumber(), getDocumentType(), getUsername(), getName(), getLastName(), getPassword(), getCreationDate(), getUpdateDate(), getState(), getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
