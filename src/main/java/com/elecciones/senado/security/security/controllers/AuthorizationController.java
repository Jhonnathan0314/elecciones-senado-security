package com.elecciones.senado.security.security.controllers;

import com.elecciones.senado.security.context.user.application.dto.UserCreateDTO;
import com.elecciones.senado.security.context.user.infrastructure.mappers.UserCreateMapper;
import com.elecciones.senado.security.security.models.AuthResponse;
import com.elecciones.senado.security.security.models.LoginRequest;
import com.elecciones.senado.security.security.service.AuthorizationService;
import com.elecciones.senado.security.utils.exceptions.DuplicatedException;
import com.elecciones.senado.security.utils.exceptions.ForbiddenException;
import com.elecciones.senado.security.utils.exceptions.InvalidBodyException;
import com.elecciones.senado.security.utils.exceptions.NoResultsException;
import com.elecciones.senado.security.utils.http.HttpUtils;
import com.elecciones.senado.security.utils.messages.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/security/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthorizationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    private final AuthorizationService authService;
    private final HttpUtils httpUtils = new HttpUtils();
    private final UserCreateMapper userCreateMapper = new UserCreateMapper();

    @PostMapping(value = "login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request) {
        logger.info("ACCION LOGIN -> Inicia proceso de login con request: " + request.toString());
        ApiResponse<AuthResponse> response = new ApiResponse<>();
        try {
            response.setData(authService.login(request));
            logger.info("ACCION LOGIN -> Finaliza proceso de login satisfactorio");
            return ResponseEntity.ok(response);
        } catch (NoResultsException | InvalidBodyException | ForbiddenException e) {
            logger.error("ACCION LOGIN -> Finaliza proceso de login con error: " + e.getMessage());
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @PostMapping(value = "register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody UserCreateDTO request) {
        logger.info("ACCION REGISTER -> Inicia proceso de register con request: " + request.toString());
        ApiResponse<AuthResponse> response = new ApiResponse<>();
        try {
            response.setData(authService.register(userCreateMapper.dtoToModel(request)));
            logger.info("ACCION REGISTER -> Finaliza proceso de register satisfactorio");
            return ResponseEntity.ok(response);
        } catch (InvalidBodyException | DuplicatedException e) {
            logger.error("ACCION REGISTER -> Finaliza proceso de register con error: " + e.getMessage());
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

}
