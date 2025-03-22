package com.devteria_tutorial.identity_service.presentation.api_response;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseEntityFactory {
    public static <T> ResponseEntity<APIResponse<T>> getResponseEntity(ResponseCode responseCode, T result) {
        return ResponseEntity.status(responseCode.getHttpStatusCode())
                .body(
                        APIResponse.<T>builder()
                                .code(responseCode.getResponseCode())
                                .message(responseCode.getResponseMessage())
                                .result(result)
                                .build()
                );
    }

    public static <T> ResponseEntity<APIResponse<T>> getResponseEntity(int code, String message, HttpStatusCode httpStatusCode, T result) {
        return ResponseEntity.status(httpStatusCode)
                .body(
                        APIResponse.<T>builder()
                                .code(code)
                                .message(message)
                                .result(result)
                                .build()
                );
    }
}
