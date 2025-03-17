package com.devteria_tutorial.identity_service.business.exception;

import com.devteria_tutorial.identity_service.business.dto.response.UserResponse;
import com.devteria_tutorial.identity_service.presentation.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<APIResponse<String>> runtimeExceptionHandler(RuntimeException e) {
        APIResponse<String> response = APIResponse.<String>builder()
                .code(400)
                .message(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AppException.class)
    ResponseEntity<APIResponse<String>> appExceptionHandler(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        APIResponse<String> response = APIResponse.<String>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<APIResponse<String>> accessDeniedExceptionHandler(AccessDeniedException e) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                APIResponse.<String>builder()
                        .code(errorCode.getCode())
                        .message("Access denied")
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<APIResponse<String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String enumKey = Objects.requireNonNull(e.getFieldError()).getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
        APIResponse<String> response = APIResponse.<String>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(ParseException.class)
    ResponseEntity<APIResponse<String>> parseExceptionHandler(ParseException e) {
        APIResponse<String> response = APIResponse.<String>builder()
                .code(400)
                .message(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
