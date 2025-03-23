package com.devteria_tutorial.identity_service.business.service;

import com.devteria_tutorial.identity_service.business.dto.request.LoginRequest;
import com.devteria_tutorial.identity_service.business.dto.request.IntrospectRequest;
import com.devteria_tutorial.identity_service.business.dto.request.LogoutRequest;
import com.devteria_tutorial.identity_service.business.dto.response.LoginResponse;
import com.devteria_tutorial.identity_service.business.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    LoginResponse authenticate(LoginRequest loginRequest);
    void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException;
    IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException;
}
