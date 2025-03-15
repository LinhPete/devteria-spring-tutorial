package com.devteria_tutorial.identity_service.business.service;

import com.devteria_tutorial.identity_service.business.dto.request.UserRequest;
import com.devteria_tutorial.identity_service.business.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUserById(String id);
    UserResponse getMyInfo();
    List<UserResponse> getAllUsers();
    UserResponse createUser(UserRequest requestUser);
    UserResponse updateUser(String id, UserRequest requestUser);
    void deleteUser(String id);
}
