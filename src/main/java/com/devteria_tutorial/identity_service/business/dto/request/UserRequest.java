package com.devteria_tutorial.identity_service.business.dto.request;

import com.devteria_tutorial.identity_service.business.dto.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    String username;
    @Size(min = 8, max = 50, message = "INVALID_PASSWORD")
    String password;
    String fullName;
    @DobConstraint(min = 20)
    LocalDate dob;
    List<String> roles;
}
