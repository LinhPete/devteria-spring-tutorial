package com.devteria_tutorial.identity_service.business.service.impl;

import com.devteria_tutorial.identity_service.business.dto.request.UserRequest;
import com.devteria_tutorial.identity_service.business.dto.response.UserResponse;
import com.devteria_tutorial.identity_service.persistence.entity.User;
import com.devteria_tutorial.identity_service.business.exception.AppException;
import com.devteria_tutorial.identity_service.business.exception.ErrorCode;
import com.devteria_tutorial.identity_service.business.mapper.UserMapper;
import com.devteria_tutorial.identity_service.persistence.enums.Role;
import com.devteria_tutorial.identity_service.persistence.repository.UserRepository;
import com.devteria_tutorial.identity_service.business.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(String id) {
        log.info("getUserById");
        User user = userRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        var name = context.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public UserResponse createUser(UserRequest requestUser) {
        if(userRepository.existsByUsername(requestUser.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(requestUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(String id, UserRequest requestUser) {
        User user = userRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, requestUser);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(String id) {
        userRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        userRepository.deleteById(id);
    }

}
