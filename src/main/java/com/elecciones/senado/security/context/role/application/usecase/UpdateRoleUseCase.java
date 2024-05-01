package com.elecciones.senado.security.context.role.application.usecase;

import com.elecciones.senado.security.context.role.domain.model.Role;
import com.elecciones.senado.security.context.role.domain.port.RoleRepository;
import com.elecciones.senado.security.utils.constants.ErrorMessages;
import com.elecciones.senado.security.utils.exceptions.InvalidBodyException;
import com.elecciones.senado.security.utils.exceptions.NoChangesException;
import com.elecciones.senado.security.utils.exceptions.NoIdReceivedException;
import com.elecciones.senado.security.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateRoleUseCase {

    private final ErrorMessages errorMessages = new ErrorMessages();

    private final RoleRepository roleRepository;

    public Role update(Role role) throws NoIdReceivedException, NoResultsException, NoChangesException, InvalidBodyException {
        if(role.getId() == null) throw new NoIdReceivedException(errorMessages.NO_ID_RECEIVED);

        if(!role.isValid(role)) throw new InvalidBodyException(errorMessages.INVALID_BODY);

        Optional<Role> optRole = roleRepository.findById(role.getId());
        if(optRole.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);

        Role roleDb = optRole.get();
        if(roleDb.getName().equals(role.getName())) throw new NoChangesException(errorMessages.NO_CHANGES);

        role.setState(roleDb.getState());
        role.setCreationDate(roleDb.getCreationDate());
        return roleRepository.update(role);
    }

}
