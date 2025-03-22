package com.devteria_tutorial.identity_service.presentation.api_response.code_enum;

import com.devteria_tutorial.identity_service.presentation.api_response.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum SuccessCode implements ResponseCode {

    USER_CREATED(100, "User created successfully", HttpStatus.CREATED),
    USER_UPDATED(101, "User updated successfully", HttpStatus.ACCEPTED),
    USER_DELETED(102, "User deleted successfully", HttpStatus.NO_CONTENT),
    USER_RETRIEVED(103, "User retrieved successfully", HttpStatus.OK),
    USERS_RETRIEVED(104, "Users retrieved successfully", HttpStatus.OK),;

    private final int responseCode;
    private final String responseMessage;
    private final HttpStatusCode httpStatusCode;
}
