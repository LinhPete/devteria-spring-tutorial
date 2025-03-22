package com.devteria_tutorial.identity_service.business.mapper;

import com.devteria_tutorial.identity_service.business.dto.request.PermissionRequest;
import com.devteria_tutorial.identity_service.business.dto.request.UserRequest;
import com.devteria_tutorial.identity_service.business.dto.response.PermissionResponse;
import com.devteria_tutorial.identity_service.business.dto.response.UserResponse;
import com.devteria_tutorial.identity_service.persistence.entity.Permission;
import com.devteria_tutorial.identity_service.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring")
@Component
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
