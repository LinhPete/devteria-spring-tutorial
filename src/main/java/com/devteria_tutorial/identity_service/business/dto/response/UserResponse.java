package com.devteria_tutorial.identity_service.business.dto.response;

import com.devteria_tutorial.identity_service.persistence.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String fullName;
    LocalDate dob;
    Set<Role> roles;
}
