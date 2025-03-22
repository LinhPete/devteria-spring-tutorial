package com.devteria_tutorial.identity_service.business.exception;

import com.devteria_tutorial.identity_service.presentation.api_response.code_enum.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;
    public AppException(ErrorCode errorCode) {
        super(errorCode.getResponseMessage());
        this.errorCode = errorCode;
    }
}
