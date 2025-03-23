package com.devteria_tutorial.identity_service.presentation.controller;

import com.devteria_tutorial.identity_service.business.dto.request.LoginRequest;
import com.devteria_tutorial.identity_service.business.dto.request.IntrospectRequest;
import com.devteria_tutorial.identity_service.business.dto.request.LogoutRequest;
import com.devteria_tutorial.identity_service.business.dto.response.LoginResponse;
import com.devteria_tutorial.identity_service.business.dto.response.IntrospectResponse;
import com.devteria_tutorial.identity_service.business.service.AuthenticationService;
import com.devteria_tutorial.identity_service.presentation.api_response.APIResponse;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/login")
    APIResponse<LoginResponse> logIn(@RequestBody LoginRequest loginRequest) {
        return APIResponse.<LoginResponse>builder()
                .code(101)
                .message("Access granted")
                .result(authenticationService.authenticate(loginRequest))
                .build();
    }

    @PostMapping("/logout")
    APIResponse<Void> logOut(@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {
        authenticationService.logout(logoutRequest);
        return APIResponse.<Void>builder()
                .code(101)
                .message("Logout success")
                .build();
    }

    @PostMapping("/introspect")
    APIResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        IntrospectResponse response = authenticationService.introspect(introspectRequest);
        return APIResponse.<IntrospectResponse>builder()
                .result(response)
                .build();
    }
}
