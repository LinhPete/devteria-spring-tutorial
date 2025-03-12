package com.devteria_tutorial.identity_service.business.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_ERROR(999,"Unknown error"),
    USER_EXISTED(400,"User already existed"),
    USER_NOT_FOUND(404,"User not found"),
    INVALID_PASSWORD(400,"Password must be at least 8 characters"),
    WRONG_PASSWORD(400,"Wrong password");

    private final int code;
    private final String message;
}
