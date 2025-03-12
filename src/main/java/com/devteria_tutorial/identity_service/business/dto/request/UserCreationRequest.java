package com.devteria_tutorial.identity_service.business.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    String username;
    @Size(min = 8, max = 50, message = "INVALID_PASSWORD")
    String password;
    String fullName;
    LocalDate dob;
}
