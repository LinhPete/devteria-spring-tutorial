package com.devteria_tutorial.identity_service.configuration;

import com.devteria_tutorial.identity_service.business.exception.AppException;
import com.devteria_tutorial.identity_service.presentation.api_response.code_enum.ErrorCode;
import com.devteria_tutorial.identity_service.persistence.entity.User;
import com.devteria_tutorial.identity_service.persistence.entity.Role;
import com.devteria_tutorial.identity_service.persistence.repository.RoleRepository;
import com.devteria_tutorial.identity_service.persistence.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AppInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if(userRepository.findByUsername("admin").isEmpty()){
                Set<Role> roles = new HashSet<>();
                var role = roleRepository.findById("ADMIN");
                roles.add(role.orElseThrow(()-> new AppException(ErrorCode.UNCATEGORIZED_ERROR)));
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.warn("admin user created with default password : admin, please change it");
            }
        };
    }
}
