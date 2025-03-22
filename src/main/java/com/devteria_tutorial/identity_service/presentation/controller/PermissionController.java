package com.devteria_tutorial.identity_service.presentation.controller;

import com.devteria_tutorial.identity_service.business.dto.request.PermissionRequest;
import com.devteria_tutorial.identity_service.business.dto.response.PermissionResponse;
import com.devteria_tutorial.identity_service.business.service.PermissionService;
import com.devteria_tutorial.identity_service.presentation.api_response.APIResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    APIResponse<PermissionResponse> create(@RequestBody PermissionRequest permissionRequest) {
        return APIResponse.<PermissionResponse>builder()
                .code(103)
                .message("Permission creation successful")
                .result(permissionService.create(permissionRequest))
                .build();
    }

    @GetMapping
    APIResponse<List<PermissionResponse>> getAll() {
        return APIResponse.<List<PermissionResponse>>builder()
                .code(103)
                .message("Permission retrieval successful")
                .result(permissionService.getAllPermissions())
                .build();
    }

    @DeleteMapping("/{name}")
    APIResponse<Void> delete(@PathVariable String name) {
        permissionService.delete(name);
        return APIResponse.<Void>builder()
                .code(103)
                .message("Permission deletion successful")
                .build();
    }
}
