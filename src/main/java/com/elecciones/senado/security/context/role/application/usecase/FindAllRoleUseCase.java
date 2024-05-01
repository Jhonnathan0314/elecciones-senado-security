package com.elecciones.senado.security.context.role.application.usecase;

import com.elecciones.senado.security.context.role.domain.model.Role;
import com.elecciones.senado.security.context.role.domain.port.RoleRepository;
import com.elecciones.senado.security.utils.constants.ErrorMessages;
import com.elecciones.senado.security.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllRoleUseCase {

    private final RoleRepository roleRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public List<Role> findAll() throws NoResultsException {
        List<Role> roles = roleRepository.findAll();
        if(roles == null || roles.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);
        return roles;
    }

}
