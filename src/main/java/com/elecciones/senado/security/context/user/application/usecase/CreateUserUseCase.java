package com.elecciones.senado.security.context.user.application.usecase;

import com.elecciones.senado.security.context.document_type.domain.model.DocumentType;
import com.elecciones.senado.security.context.document_type.domain.port.DocumentTypeRepository;
import com.elecciones.senado.security.context.user.domain.model.User;
import com.elecciones.senado.security.context.user.domain.port.UserRepository;
import com.elecciones.senado.security.utils.constants.ErrorMessages;
import com.elecciones.senado.security.utils.exceptions.DuplicatedException;
import com.elecciones.senado.security.utils.exceptions.InvalidBodyException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();
    private final PasswordEncoder passwordEncoder;

    public User create(User user) throws DuplicatedException, InvalidBodyException {

        if(user.getPassword() == null) throw new InvalidBodyException(errorMessages.INVALID_BODY);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(!user.isValid(user)) throw new InvalidBodyException(errorMessages.INVALID_BODY);

        if(userRepository.findByUsername(user.getUsername()).isPresent()) throw new DuplicatedException(errorMessages.DUPLICATED);

        Optional<DocumentType> optionalDocumentType = documentTypeRepository.findByPrefix(user.getDocumentType().getPrefix());
        if(optionalDocumentType.isEmpty()) throw new InvalidBodyException(errorMessages.BAD_DOCUMENT_TYPE);
        user.setDocumentType(optionalDocumentType.get());


        return userRepository.create(user);
    }

}
