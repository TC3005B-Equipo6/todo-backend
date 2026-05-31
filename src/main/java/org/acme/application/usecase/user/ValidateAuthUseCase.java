package org.acme.application.usecase.user;

import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.domain.model.User;
import org.acme.infrastructure.security.AuthContext;

@ApplicationScoped
public class ValidateAuthUseCase {

    private final AuthContext authContext;

    @Inject
    public ValidateAuthUseCase(AuthContext authContext){
        this.authContext = authContext;
    }

    public User execute() {
        User user = authContext.getUser();

        if(user == null){
            throw new UnauthorizedException();
        }

        return user;
    }
}
