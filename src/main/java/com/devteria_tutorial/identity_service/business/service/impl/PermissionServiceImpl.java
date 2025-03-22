package com.devteria_tutorial.identity_service.business.service.impl;

import com.devteria_tutorial.identity_service.business.dto.request.PermissionRequest;
import com.devteria_tutorial.identity_service.business.dto.response.PermissionResponse;
import com.devteria_tutorial.identity_service.business.exception.AppException;
import com.devteria_tutorial.identity_service.presentation.api_response.code_enum.ErrorCode;
import com.devteria_tutorial.identity_service.business.mapper.PermissionMapper;
import com.devteria_tutorial.identity_service.business.service.PermissionService;
import com.devteria_tutorial.identity_service.persistence.entity.Permission;
import com.devteria_tutorial.identity_service.persistence.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse create(PermissionRequest permissionRequest) {
        Permission permission = permissionMapper.toPermission(permissionRequest);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getAllPermissions() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void delete(String name) {
        permissionRepository.findById(name).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_ERROR));
        permissionRepository.deleteById(name);
    }

}
