package com.devteria_tutorial.identity_service.presentation.controller;

import com.devteria_tutorial.identity_service.business.dto.request.RoleRequest;
import com.devteria_tutorial.identity_service.business.dto.response.RoleResponse;
import com.devteria_tutorial.identity_service.business.service.RoleService;
import com.devteria_tutorial.identity_service.presentation.api_response.APIResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    APIResponse<RoleResponse> create(@RequestBody RoleRequest roleRequest) {
        return APIResponse.<RoleResponse>builder()
                .code(103)
                .message("Role creation successful")
                .result(roleService.createRole(roleRequest))
                .build();
    }

    @GetMapping
    APIResponse<List<RoleResponse>> getAll() {
        return APIResponse.<List<RoleResponse>>builder()
                .code(103)
                .message("Role retrieval successful")
                .result(roleService.getAllRoles())
                .build();
    }

    @DeleteMapping("/{name}")
    APIResponse<Void> delete(@PathVariable String name) {
        roleService.deleteRole(name);
        return APIResponse.<Void>builder()
                .code(103)
                .message("Role deletion successful")
                .build();
    }
}
