package com.elecciones.senado.security.context.user.application.usecase;

import com.elecciones.senado.security.context.document_type.domain.model.DocumentType;
import com.elecciones.senado.security.context.document_type.domain.port.DocumentTypeRepository;
import com.elecciones.senado.security.context.user.domain.model.User;
import com.elecciones.senado.security.context.user.domain.port.UserRepository;
import com.elecciones.senado.security.utils.constants.ErrorMessages;
import com.elecciones.senado.security.utils.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final ErrorMessages errorMessages = new ErrorMessages();
    private final UserRepository userRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final PasswordEncoder passwordEncoder;

    public User update(User user) throws NoIdReceivedException, NoResultsException, NoChangesException, InvalidBodyException, DuplicatedException {

        if(user.getId() == null) throw new NoIdReceivedException(errorMessages.NO_ID_RECEIVED);

        if(!user.isValid(user)) throw new InvalidBodyException(errorMessages.INVALID_BODY);

        Optional<User> optUser = userRepository.findById(user.getId());
        if(optUser.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);

        User userDb = optUser.get();

        if(!user.getDocumentType().getPrefix().equals(userDb.getDocumentType().getPrefix())) {
            Optional<DocumentType> optionalDocumentType = documentTypeRepository.findByPrefix(user.getDocumentType().getPrefix());
            if(optionalDocumentType.isEmpty()) throw new InvalidBodyException(errorMessages.BAD_DOCUMENT_TYPE);
            user.setDocumentType(optionalDocumentType.get());
        }

        user.setRole(userDb.getRole());
        user.setState(userDb.getState());

        Optional<DocumentType> optionalDocumentType = documentTypeRepository.findByPrefix(user.getDocumentType().getPrefix());
        if(optionalDocumentType.isEmpty()) throw new InvalidBodyException(errorMessages.BAD_DOCUMENT_TYPE);
        user.setDocumentType(optionalDocumentType.get());

        if(userDb.equals(user) && (user.getPassword() == null || user.getPassword().isEmpty())) throw new NoChangesException(errorMessages.NO_CHANGES);

        if(userRepository.findByUsername(user.getUsername()).isPresent()) throw new DuplicatedException(errorMessages.DUPLICATED);

        if(user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(userDb.getPassword());
        }

        return userRepository.update(user);
    }

}
