package com.elecciones.senado.security.context.user.application.usecase;

import com.elecciones.senado.security.context.user.domain.model.User;
import com.elecciones.senado.security.context.user.domain.port.UserRepository;
import com.elecciones.senado.security.utils.constants.ErrorMessages;
import com.elecciones.senado.security.utils.exceptions.NonExistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteByIdUserUseCase {

    private final UserRepository userRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public void deleteById(Long id) throws NonExistenceException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new NonExistenceException(errorMessages.NON_EXISTENT_DATA);
        userRepository.deleteById(id);
    }

}
