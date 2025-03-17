package com.devteria_tutorial.identity_service.business.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_ERROR(999,"Unknown error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(400,"User already existed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(404,"User not found", HttpStatus.NOT_FOUND),
    INVALID_PASSWORD(400,"Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    WRONG_PASSWORD(400,"Wrong password", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(401,"Unauthorized", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(401,"Unauthorized client", HttpStatus.FORBIDDEN),;

    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;
}
