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
public class ChangeStateByIdUserUseCase {

    private final UserRepository userRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public User changeStateById(Long id) throws NonExistenceException {
        Optional<User> optUser = userRepository.findById(id);
        if(optUser.isEmpty()) throw new NonExistenceException(errorMessages.NON_EXISTENT_DATA);
        User user = optUser.get();
        user.setState(user.getState().equals("active") ? "inactive" : "active");
        user = userRepository.update(user);
        return user;
    }
}
