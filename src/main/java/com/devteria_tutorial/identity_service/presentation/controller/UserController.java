package com.devteria_tutorial.identity_service.presentation.controller;

import com.devteria_tutorial.identity_service.business.dto.request.UserRequest;
import com.devteria_tutorial.identity_service.presentation.api_response.APIResponse;
import com.devteria_tutorial.identity_service.business.dto.response.UserResponse;
import com.devteria_tutorial.identity_service.business.service.UserService;
import com.devteria_tutorial.identity_service.presentation.api_response.ResponseEntityFactory;
import com.devteria_tutorial.identity_service.presentation.api_response.code_enum.SuccessCode;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ResponseEntity<APIResponse<UserResponse>> createUser(@RequestBody @Valid UserRequest requestUser) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("{} : create user", authentication.getName());
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.USER_CREATED,
                userService.createUser(requestUser)
        );
    }

    @GetMapping
    ResponseEntity<APIResponse<List<UserResponse>>> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("{} : get all user", authentication.getName());
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.USERS_RETRIEVED,
                userService.getAllUsers()
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<APIResponse<UserResponse>> getUserById(@PathVariable String id) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("{} : get user by id", authentication.getName());
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.USER_RETRIEVED,
                userService.getUserById(id)
        );
    }

    @GetMapping("/my-info")
    ResponseEntity<APIResponse<UserResponse>> getMyInfo() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("{} : get profile", authentication.getName());
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.USER_RETRIEVED,
                userService.getMyInfo()
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<APIResponse<UserResponse>> updateUser(@PathVariable String id, @RequestBody UserRequest requestUser) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("{} : update user", authentication.getName());
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.USER_UPDATED,
                userService.updateUser(id, requestUser)
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse<String>> deleteUser(@PathVariable String id) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("{} : delete user", authentication.getName());
        userService.deleteUser(id);
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.USER_DELETED,
                null
        );
    }
}
