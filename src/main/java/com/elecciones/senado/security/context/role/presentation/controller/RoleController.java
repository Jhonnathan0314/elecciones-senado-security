package com.elecciones.senado.security.context.role.presentation.controller;

import com.elecciones.senado.security.context.role.application.dto.RoleCreateDTO;
import com.elecciones.senado.security.context.role.application.dto.RoleResponseDTO;
import com.elecciones.senado.security.context.role.application.dto.RoleUpdateDTO;
import com.elecciones.senado.security.context.role.application.usecase.*;
import com.elecciones.senado.security.context.role.domain.model.Role;
import com.elecciones.senado.security.context.role.infrastructure.mappers.RoleCreateMapper;
import com.elecciones.senado.security.context.role.infrastructure.mappers.RoleResponseMapper;
import com.elecciones.senado.security.context.role.infrastructure.mappers.RoleUpdateMapper;
import com.elecciones.senado.security.utils.exceptions.*;
import com.elecciones.senado.security.utils.http.HttpUtils;
import com.elecciones.senado.security.utils.messages.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/security/role")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RoleController {

    private final FindAllRoleUseCase findAllRoleUseCase;
    private final FindByIdRoleUseCase findByIdRoleUseCase;
    private final CreateRoleUseCase createRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;
    private final DeleteByIdRoleUseCase deleteByIdRoleUseCase;
    private final ChangeStateByIdRoleUseCase changeStateByIdRoleUseCase;

    private final RoleCreateMapper roleCreateMapper = new RoleCreateMapper();
    private final RoleUpdateMapper roleUpdateMapper = new RoleUpdateMapper();
    private final RoleResponseMapper roleResponseMapper = new RoleResponseMapper();
    private final HttpUtils httpUtils = new HttpUtils();

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleResponseDTO>>> findAll() {
        ApiResponse<List<RoleResponseDTO>> response = new ApiResponse<>();
        try {
            List<RoleResponseDTO> roles = roleResponseMapper.modelsToDtos(findAllRoleUseCase.findAll());
            response.setData(roles);
            return ResponseEntity.ok(response);
        } catch (NoResultsException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponseDTO>> findById(@PathVariable Long id) {
        ApiResponse<RoleResponseDTO> response = new ApiResponse<>();
        try {
            Role role = findByIdRoleUseCase.findById(id);
            response.setData(roleResponseMapper.modelToDto(role));
            return ResponseEntity.ok(response);
        } catch (NoResultsException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponseDTO>> create(@RequestBody RoleCreateDTO role) {
        ApiResponse<RoleResponseDTO> response = new ApiResponse<>();
        try {
            response.setData(roleResponseMapper.modelToDto(createRoleUseCase.create(roleCreateMapper.dtoToModel(role))));
            return ResponseEntity.ok(response);
        } catch (DuplicatedException | InvalidBodyException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<RoleResponseDTO>> update(@RequestBody RoleUpdateDTO role) {
        ApiResponse<RoleResponseDTO> response = new ApiResponse<>();
        try {
            response.setData(roleResponseMapper.modelToDto(updateRoleUseCase.update(roleUpdateMapper.dtoToModel(role))));
            return ResponseEntity.ok(response);
        } catch (NoIdReceivedException | InvalidBodyException | NoResultsException | NoChangesException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteById(@PathVariable Long id) {
        ApiResponse<Object> response = new ApiResponse<>();
        try {
            deleteByIdRoleUseCase.deleteById(id);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (NonExistenceException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @DeleteMapping("/change-state/{id}")
    public ResponseEntity<ApiResponse<RoleUpdateDTO>> changeStateById(@PathVariable Long id) {
        ApiResponse<RoleUpdateDTO> response = new ApiResponse<>();
        try {
            Role role = changeStateByIdRoleUseCase.changeStateById(id);
            response.setData(roleUpdateMapper.modelToDto(role));
            return ResponseEntity.ok(response);
        } catch (NonExistenceException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

}
