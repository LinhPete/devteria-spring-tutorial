package com.devteria_tutorial.identity_service.presentation.controller;

import com.devteria_tutorial.identity_service.business.dto.request.UserCreationRequest;
import com.devteria_tutorial.identity_service.presentation.APIResponse;
import com.devteria_tutorial.identity_service.business.dto.response.UserResponse;
import com.devteria_tutorial.identity_service.business.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    APIResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest requestUser) {
        return APIResponse.<UserResponse>builder()
                .code(100)
                .message("Successfully created user")
                .result(userService.createUser(requestUser))
                .build();
    }

    @GetMapping
    APIResponse<List<UserResponse>> getAllUsers() {
        return APIResponse.<List<UserResponse>>builder()
                .code(100)
                .message("Successfully retrieved users")
                .result(userService.getAllUsers())
                .build();
    }

    @GetMapping("/{id}")
    APIResponse<UserResponse> getUserById(@PathVariable String id) {
        return APIResponse.<UserResponse>builder()
                .code(100)
                .message("Successfully retrieved user")
                .result(userService.getUserById(id))
                .build();
    }

    @PutMapping("/{id}")
    APIResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserCreationRequest requestUser) {
        return APIResponse.<UserResponse>builder()
                .code(100)
                .message("Successfully updated user")
                .result(userService.updateUser(id, requestUser))
                .build();
    }

    @DeleteMapping("/{id}")
    APIResponse<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return APIResponse.<String>builder()
                .code(100)
                .message("Successfully deleted user")
                .build();
    }
}
