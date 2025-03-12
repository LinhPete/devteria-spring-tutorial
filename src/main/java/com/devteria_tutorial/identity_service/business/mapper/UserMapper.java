package com.devteria_tutorial.identity_service.business.mapper;

import com.devteria_tutorial.identity_service.business.dto.request.UserCreationRequest;
import com.devteria_tutorial.identity_service.business.dto.response.UserResponse;
import com.devteria_tutorial.identity_service.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring")
@Component
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserCreationRequest request);
    //@Mapping(source = "username",target = "fullName")
    //@Mapping(target = "fullName", ignore = true)
    UserResponse toUserResponse(User user);
}
