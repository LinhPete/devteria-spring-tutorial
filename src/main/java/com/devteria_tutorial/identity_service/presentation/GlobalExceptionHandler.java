package com.devteria_tutorial.identity_service.presentation;

import com.devteria_tutorial.identity_service.business.exception.AppException;
import com.devteria_tutorial.identity_service.presentation.api_response.code_enum.ErrorCode;
import com.devteria_tutorial.identity_service.presentation.api_response.APIResponse;
import com.devteria_tutorial.identity_service.presentation.api_response.ResponseEntityFactory;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<APIResponse<String>> runtimeExceptionHandler(RuntimeException e) {
        return ResponseEntityFactory.getResponseEntity(
                ErrorCode.UNCATEGORIZED_ERROR, null
        );
    }

    @ExceptionHandler(AppException.class)
    ResponseEntity<APIResponse<String>> appExceptionHandler(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntityFactory.getResponseEntity(
                errorCode, null
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<APIResponse<String>> accessDeniedExceptionHandler(AccessDeniedException e) {
        return ResponseEntityFactory.getResponseEntity(
                ErrorCode.UNAUTHORIZED, null
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<APIResponse<String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String enumKey = Objects.requireNonNull(e.getFieldError()).getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
        var constraintViolations = e.getBindingResult().getAllErrors().getFirst().unwrap(ConstraintViolation.class);
        Map<String, Object> attributes = constraintViolations.getConstraintDescriptor().getAttributes();

        return ResponseEntityFactory.getResponseEntity(
                errorCode.getResponseCode(),
                mapAttributes(errorCode.getResponseMessage(), attributes),
                errorCode.getHttpStatusCode(),
                null
        );
    }

    @ExceptionHandler(ParseException.class)
    ResponseEntity<APIResponse<String>> parseExceptionHandler(ParseException e) {
        return ResponseEntityFactory.getResponseEntity(
                ErrorCode.UNCATEGORIZED_ERROR, null
        );
    }

    private String mapAttributes (String message, Map<String, Object> attributes) {
        String minValue = String.valueOf(attributes.get("min"));
        return message.replace("{min}", minValue);
    }
}
