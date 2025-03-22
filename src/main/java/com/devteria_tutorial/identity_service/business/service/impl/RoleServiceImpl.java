package com.devteria_tutorial.identity_service.business.service.impl;

import com.devteria_tutorial.identity_service.business.dto.request.RoleRequest;
import com.devteria_tutorial.identity_service.business.dto.response.RoleResponse;
import com.devteria_tutorial.identity_service.business.mapper.RoleMapper;
import com.devteria_tutorial.identity_service.business.service.RoleService;
import com.devteria_tutorial.identity_service.persistence.repository.PermissionRepository;
import com.devteria_tutorial.identity_service.persistence.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @Override
    public RoleResponse createRole(RoleRequest roleRequest) {
        var role = roleMapper.toRole(roleRequest);
        var permissions = permissionRepository.findAllById(roleRequest.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        var savedRole = roleRepository.save(role);
        return roleMapper.toRoleResponse(savedRole);
    }

    @Override
    public void deleteRole(String roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }
}
