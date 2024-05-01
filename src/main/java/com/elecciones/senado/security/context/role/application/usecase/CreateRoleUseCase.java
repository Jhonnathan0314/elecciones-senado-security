package com.elecciones.senado.security.context.role.application.usecase;

import com.elecciones.senado.security.context.role.domain.model.Role;
import com.elecciones.senado.security.context.role.domain.port.RoleRepository;
import com.elecciones.senado.security.utils.constants.ErrorMessages;
import com.elecciones.senado.security.utils.exceptions.DuplicatedException;
import com.elecciones.senado.security.utils.exceptions.InvalidBodyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRoleUseCase {

    private final RoleRepository roleRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public Role create(Role role) throws DuplicatedException, InvalidBodyException {
        if(!role.isValid(role)) throw new InvalidBodyException(errorMessages.INVALID_BODY);
        if(roleRepository.findByName(role.getName()).isPresent()) throw new DuplicatedException(errorMessages.DUPLICATED);
        return roleRepository.create(role);
    }

}
