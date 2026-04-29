package org.acme.application.usecase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.domain.models.User;
import org.acme.domain.repository.AuthRepository;

@ApplicationScoped
public class LoginUseCase {

    private final AuthRepository authRepository;

    @Inject
    public LoginUseCase(AuthRepository authRepository){
        this.authRepository = authRepository;
    }

    public Response execute(String authHeader) {
        return authRepository.validateToken(authHeader);
    }
}
