package com.devteria_tutorial.identity_service.business.service;

import com.devteria_tutorial.identity_service.business.dto.request.PermissionRequest;
import com.devteria_tutorial.identity_service.business.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse create(PermissionRequest permissionRequest);
    List<PermissionResponse> getAllPermissions();
    void delete(String name);
}
