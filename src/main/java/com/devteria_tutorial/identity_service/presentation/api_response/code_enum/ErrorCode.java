package com.devteria_tutorial.identity_service.presentation.api_response.code_enum;

import com.devteria_tutorial.identity_service.presentation.api_response.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ResponseCode {
    UNCATEGORIZED_ERROR(999,"Unknown error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(400,"User already existed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(401,"User not found", HttpStatus.NOT_FOUND),
    INVALID_PASSWORD(402,"Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    WRONG_PASSWORD(403,"Wrong password", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(404,"Must login to continue", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(405,"Unauthorized client", HttpStatus.FORBIDDEN),
    INVALID_DOB(406, "Age must be at least {min}", HttpStatus.BAD_REQUEST),;

    private final int responseCode;
    private final String responseMessage;
    private final HttpStatusCode httpStatusCode;
}
