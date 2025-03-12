package com.devteria_tutorial.identity_service.business.service;

import com.devteria_tutorial.identity_service.business.dto.request.AuthRequest;
import com.devteria_tutorial.identity_service.business.dto.request.IntrospectRequest;
import com.devteria_tutorial.identity_service.business.dto.response.AuthResponse;
import com.devteria_tutorial.identity_service.business.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthResponse authenticate(AuthRequest authRequest);
    IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException;
}
