package com.devteria_tutorial.identity_service.business.mapper;

import com.devteria_tutorial.identity_service.business.dto.request.PermissionRequest;
import com.devteria_tutorial.identity_service.business.dto.request.RoleRequest;
import com.devteria_tutorial.identity_service.business.dto.response.PermissionResponse;
import com.devteria_tutorial.identity_service.business.dto.response.RoleResponse;
import com.devteria_tutorial.identity_service.persistence.entity.Permission;
import com.devteria_tutorial.identity_service.persistence.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring")
@Component
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
