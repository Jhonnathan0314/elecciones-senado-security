package com.elecciones.senado.security.security.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    String username;
    String password;

    public boolean isValidRequest(LoginRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) return false;
        return !request.getUsername().isEmpty() && !request.getPassword().isEmpty();
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='******'" +
                '}';
    }
}
