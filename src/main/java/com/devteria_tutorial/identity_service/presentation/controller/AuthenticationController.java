package com.devteria_tutorial.identity_service.presentation.controller;

import com.devteria_tutorial.identity_service.business.dto.request.AuthRequest;
import com.devteria_tutorial.identity_service.business.dto.response.AuthResponse;
import com.devteria_tutorial.identity_service.business.service.AuthenticationService;
import com.devteria_tutorial.identity_service.presentation.APIResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/log-in")
    APIResponse<AuthResponse> logIn(@RequestBody AuthRequest authRequest) {
        return APIResponse.<AuthResponse>builder()
                .code(101)
                .message("Access granted")
                .result(authenticationService.authenticate(authRequest))
                .build();
    }
}
