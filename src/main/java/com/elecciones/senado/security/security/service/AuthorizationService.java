package com.elecciones.senado.security.security.service;

import com.elecciones.senado.security.context.user.application.usecase.CreateUserUseCase;
import com.elecciones.senado.security.context.user.application.usecase.FindByUsernameUserUseCase;
import com.elecciones.senado.security.context.user.domain.model.User;
import com.elecciones.senado.security.security.jwt.JwtService;
import com.elecciones.senado.security.security.models.AuthResponse;
import com.elecciones.senado.security.security.models.LoginRequest;
import com.elecciones.senado.security.utils.constants.ErrorMessages;
import com.elecciones.senado.security.utils.exceptions.DuplicatedException;
import com.elecciones.senado.security.utils.exceptions.ForbiddenException;
import com.elecciones.senado.security.utils.exceptions.InvalidBodyException;
import com.elecciones.senado.security.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);

    private final ErrorMessages errorMessages = new ErrorMessages();
    private final FindByUsernameUserUseCase findByUsernameUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) throws NoResultsException, InvalidBodyException, ForbiddenException {

        if(!request.isValidRequest(request)) throw new InvalidBodyException(errorMessages.INVALID_BODY);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception e) {
            throw new ForbiddenException(errorMessages.FORBIDDEN);
        }

        Optional<User> userDb = findByUsernameUserUseCase.findByUsername(request.getUsername());
        if(userDb.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);

        Map<String, String> extraClaims = new HashMap<>();
        extraClaims.put("user_role", userDb.get().getRole().getName());

        String token = jwtService.generateToken(userDb.get(), extraClaims);

        logger.info("ACCION LOGIN -> Usuario logueado, token generado correctamente");
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(User request) throws InvalidBodyException, DuplicatedException {
        User user = User.builder()
                .documentType(request.getDocumentType())
                .documentNumber(request.getDocumentNumber())
                .username(request.getUsername())
                .password(request.getPassword())
                .name(request.getName())
                .lastName(request.getLastName())
                .build();

        user = createUserUseCase.create(user);

        Map<String, String> extraClaims = new HashMap<>();
        extraClaims.put("user_role", user.getRole().getName());

        logger.info("ACCION REGISTER -> Usuario registrado con exito, token generado correctamente");
        return AuthResponse.builder()
                .token(jwtService.generateToken(user, extraClaims))
                .build();
    }

}
