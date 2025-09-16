package com.dishxpe.svcuser.service.registration;

import com.dishxpe.svcuser.model.user.UserModel;
import com.dishxpe.svcuser.model.role.Role;
import com.dishxpe.svcuser.repository.UserRepository;
import com.dishxpe.svcuser.service.security.PasswordEncoderService;
import com.dishxpe.svcuser.service.validation.UserValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserRegistration implements RegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(UserRegistration.class);
    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;
    private final UserValidationService userValidationService;

    public UserRegistration(UserRepository userRepository, PasswordEncoderService passwordEncoderService, UserValidationService userValidationService) {
        this.userRepository = userRepository;
        this.passwordEncoderService = passwordEncoderService;
        this.userValidationService = userValidationService;
    }

    @Override
    public UserModel register(UserModel user) {

        userValidationService.validateRegistration(user.getUsername(), user.getEmail());

        user.setPassword(passwordEncoderService.encode(user.getPassword()));

        user.setRole(user.getRole() == null ? Role.USER : user.getRole());

        java.time.Instant now = java.time.Instant.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        try {
            return userRepository.save(user);
        } catch (Exception e) { // Catch any exceptions during save operation
            logger.error("Exception during user registration", e);
            throw new RuntimeException("Failed to register user"); // Rethrow as a runtime exception
        }
    }
}