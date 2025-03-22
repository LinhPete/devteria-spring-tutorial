package com.devteria_tutorial.identity_service.business.service;

import com.devteria_tutorial.identity_service.business.dto.request.RoleRequest;
import com.devteria_tutorial.identity_service.business.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleRequest roleRequest);
    List<RoleResponse> getAllRoles();
    void deleteRole(String roleId);
}
