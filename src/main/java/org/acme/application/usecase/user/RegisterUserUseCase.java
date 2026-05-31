package org.acme.application.usecase.user;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.application.dto.todo.RegisterUserDTO;
import org.acme.domain.model.User;
import org.acme.domain.repository.UserRepository;
import org.acme.infrastructure.firebase.FirebaseUserCreator;

import java.util.UUID;

@ApplicationScoped
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final FirebaseUserCreator firebaseUserCreator;

    @Inject
    public RegisterUserUseCase(UserRepository userRepository, FirebaseUserCreator firebaseUserCreator) {
        this.userRepository = userRepository;
        this.firebaseUserCreator = firebaseUserCreator;
    }

    public User execute(RegisterUserDTO registerUserDto) throws FirebaseAuthException {
        User user = new User();
        user.setEmail(registerUserDto.email());
        user.setFullName(registerUserDto.fullName());
        user.setRole("USER");
        user.setActive(true);
        user.setId(UUID.randomUUID());
        UserRecord firebaseUserRecord = firebaseUserCreator.create(user.getEmail(), registerUserDto.password());
        user.setFirebaseUuid(firebaseUserRecord.getUid());
        return userRepository.create(user);
    }
}