package com.elecciones.senado.security.context.user.application.usecase;

import com.elecciones.senado.security.context.user.domain.model.User;
import com.elecciones.senado.security.context.user.domain.port.UserRepository;
import com.elecciones.senado.security.utils.constants.ErrorMessages;
import com.elecciones.senado.security.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllUserUseCase {

    private final UserRepository userRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public List<User> findAll() throws NoResultsException {
        List<User> users = userRepository.findAll();
        if(users == null || users.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);
        return users;
    }

}
