package com.devteria_tutorial.identity_service.presentation.api_response;

import org.springframework.http.HttpStatusCode;

public interface ResponseCode {
    int getResponseCode();
    String getResponseMessage();
    HttpStatusCode getHttpStatusCode();
}
