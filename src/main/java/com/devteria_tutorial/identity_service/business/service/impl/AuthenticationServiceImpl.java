package com.devteria_tutorial.identity_service.business.service.impl;

import com.devteria_tutorial.identity_service.business.dto.request.LoginRequest;
import com.devteria_tutorial.identity_service.business.dto.request.IntrospectRequest;
import com.devteria_tutorial.identity_service.business.dto.request.LogoutRequest;
import com.devteria_tutorial.identity_service.business.dto.response.LoginResponse;
import com.devteria_tutorial.identity_service.business.dto.response.IntrospectResponse;
import com.devteria_tutorial.identity_service.business.exception.AppException;
import com.devteria_tutorial.identity_service.persistence.entity.InvalidatedToken;
import com.devteria_tutorial.identity_service.persistence.repository.InvalidatedTokenRepository;
import com.devteria_tutorial.identity_service.presentation.api_response.code_enum.ErrorCode;
import com.devteria_tutorial.identity_service.business.service.AuthenticationService;
import com.devteria_tutorial.identity_service.persistence.entity.User;
import com.devteria_tutorial.identity_service.persistence.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!matches) {
            throw new AppException(ErrorCode.WRONG_PASSWORD);
        }
        return LoginResponse.builder()
                .success(true)
                .token(generateToken(user))
                .build();
    }

    @Override
    public void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException {
        var token = verifyToken(logoutRequest.getToken()).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
        String jit = token.getJWTClaimsSet().getJWTID();
        Date expiryTime = token.getJWTClaimsSet().getExpirationTime();
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
    }

    private Optional<SignedJWT> verifyToken(String token) throws ParseException, JOSEException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        String jit = signedJWT.getJWTClaimsSet().getJWTID();
        boolean isInvalidatedToken = invalidatedTokenRepository.existsById(jit);
        Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
        if (!isInvalidatedToken && signedJWT.verify(verifier) && expiration.after(new Date())) {
            return Optional.of(signedJWT);
        }
        return Optional.empty();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        var token = introspectRequest.getToken();
        return IntrospectResponse.builder()
                .valid(verifyToken(token).isPresent())
                .build();
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("peterlinh")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", buildScope(user))
                .jwtID(UUID.randomUUID().toString())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Generate token failed", e);
            throw new AppException(ErrorCode.UNCATEGORIZED_ERROR);
        }
    }

    private String buildScope(User user) {
        StringJoiner joiner = new StringJoiner(" ");
        if (!user.getRoles().isEmpty()) {
            user.getRoles().forEach(role -> {
                joiner.add("ROLE_" + role.getName());
                if (!role.getPermissions().isEmpty()) {
                    role.getPermissions().forEach(permission -> joiner.add("PERMISSION_" + permission.getName()));
                }
            });
        }
        return joiner.toString();
    }
}
