package com.devteria_tutorial.identity_service.business.service;

import com.devteria_tutorial.identity_service.business.dto.request.AuthRequest;
import com.devteria_tutorial.identity_service.business.dto.response.AuthResponse;

public interface AuthenticationService {
    AuthResponse authenticate(AuthRequest authRequest);
}
