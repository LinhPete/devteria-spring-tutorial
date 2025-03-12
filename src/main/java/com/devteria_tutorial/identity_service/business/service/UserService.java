package com.devteria_tutorial.identity_service.business.service;

import com.devteria_tutorial.identity_service.business.dto.request.UserCreationRequest;
import com.devteria_tutorial.identity_service.business.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUserById(String id);
    List<UserResponse> getAllUsers();
    UserResponse createUser(UserCreationRequest requestUser);
    UserResponse updateUser(String id, UserCreationRequest requestUser);
    void deleteUser(String id);
}
