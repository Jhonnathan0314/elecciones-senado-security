package com.elecciones.senado.security.context.role.application.usecase;

import com.elecciones.senado.security.context.role.domain.model.Role;
import com.elecciones.senado.security.context.role.domain.port.RoleRepository;
import com.elecciones.senado.security.utils.constants.ErrorMessages;
import com.elecciones.senado.security.utils.exceptions.NonExistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChangeStateByIdRoleUseCase {

    private final RoleRepository roleRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public Role changeStateById(Long id) throws NonExistenceException {
        Optional<Role> optRole = roleRepository.findById(id);
        if(optRole.isEmpty()) throw new NonExistenceException(errorMessages.NON_EXISTENT_DATA);
        Role role = optRole.get();
        role.setState(role.getState().equals("active") ? "inactive" : "active");
        role = roleRepository.update(role);
        return role;
    }

}
